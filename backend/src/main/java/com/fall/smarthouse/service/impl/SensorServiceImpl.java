package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.constant.RiskIndex;
import com.fall.smarthouse.mapper.AbnormalMapper;
import com.fall.smarthouse.mapper.SensorMapper;
import com.fall.smarthouse.model.Abnormal;
import com.fall.smarthouse.model.Sensor;
import com.fall.smarthouse.service.ISensorService;
import com.fall.smarthouse.util.DateConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

/**
 * @author FAll
 * @date 2022/12/2 17:21
 */
@Service
public class SensorServiceImpl implements ISensorService {

    private static Integer abnormalType = null;
    private static Long startTime = null;

    @Autowired
    AbnormalMapper abnormalMapper;
    @Autowired
    SensorMapper sensorMapper;

    @Override
    public boolean insertToSensor(Sensor sensorRequest) throws ParseException {
        Map<String, Object> map = SafetyJudgment(sensorRequest);
        if(!map.isEmpty() && abnormalType == null){
            Integer riskIndex = (Integer) map.get("riskIndex");
            //将数据异常信息添加到异常表中
            insertAbnormal(new Abnormal(sensorRequest.getTime(), sensorRequest.getTime(),riskIndex));
            //将异常系数及开始时间存入hashSet
            abnormalType = riskIndex;
            startTime = sensorRequest.getTime();
        }else if(!map.isEmpty() && abnormalType != null){
            Integer riskIndexForMap = (Integer)map.get("riskIndex");
            //比较异常系数是否相等
            if(riskIndexForMap != abnormalType){
                insertAbnormal(new Abnormal(sensorRequest.getTime(),
                        sensorRequest.getTime(),riskIndexForMap));
                abnormalType = riskIndexForMap;
                startTime = sensorRequest.getTime();
            }else {
                updateAbnormal(new Abnormal(startTime,sensorRequest.getTime(),abnormalType));
            }

        } else if(map.isEmpty() && abnormalType != null){
            abnormalType = null;
            startTime = null;
        }
        Sensor sensor = new Sensor(sensorRequest.getTime()/1000, sensorRequest.getGas(), sensorRequest.getSmog(),
                sensorRequest.getTemperature(),sensorRequest.getHumidity() , sensorRequest.getShake());
        Integer affectRows = sensorMapper.insertToSensor(sensor);
        return !(affectRows == 0);
    }

    @Override
    public PageInfo<Double> getGasSensorData(String minTime, String maxTime, Integer pageNum, Integer pageSize) throws ParseException {
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        PageHelper.startPage(pageNum,pageSize);
        List<Double> gasData = sensorMapper.selectGasSensorData(minDate, maxDate);
        PageInfo<Double> gasPageInfo = new PageInfo<>(gasData);
        return gasPageInfo;
    }

    @Override
    public PageInfo<Double> getSmogSensorData(String minTime, String maxTime,Integer pageNum,Integer pageSize) throws ParseException {
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        PageHelper.startPage(pageNum,pageSize);
        List<Double> smogSensorData = sensorMapper.selectSmogSensorData(minDate, maxDate);
        PageInfo<Double> smogPageInfo = new PageInfo<>(smogSensorData);
        return smogPageInfo;
    }

    @Override
    public PageInfo<Double> getTemperatureSensorData(String minTime, String maxTime,Integer pageNum,Integer pageSize) throws ParseException {
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        PageHelper.startPage(pageNum,pageSize);
        List<Double> temperatureSensorData = sensorMapper.selectTemperatureSensorData(minDate, maxDate);
        PageInfo<Double> temperaturePageInfo = new PageInfo<>(temperatureSensorData);
        return temperaturePageInfo;
    }

    @Override
    public PageInfo<Double> getHumiditySensorData(String minTime, String maxTime,Integer pageNum,Integer pageSize) throws ParseException {
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        PageHelper.startPage(pageNum,pageSize);
        List<Double> humiditySensorData = sensorMapper.selectHumiditySensorData(minDate, maxDate);
        PageInfo<Double> humidityPageInfo = new PageInfo<>(humiditySensorData);
        return humidityPageInfo;
    }

    @Override
    public PageInfo<Double> getShakeSensorData(String minTime, String maxTime,Integer pageNum,Integer pageSize) throws ParseException {
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        PageHelper.startPage(pageNum,pageSize);
        List<Double> shakeSensorData = sensorMapper.selectShakeSensorData(minDate, maxDate);
        PageInfo<Double> shakePageInfo = new PageInfo<>(shakeSensorData);
        return shakePageInfo;
    }

    @Override
    public Map<String, Object> safetyInspection(String time) {
        Timestamp timestamp = DateConverter.StringToTimeStamp(time);
        List<Sensor> sensors = sensorMapper.pollingSelectSensorData(timestamp);
        HashMap<String, Object> map = new HashMap<>();
        for (Sensor sensor : sensors) {
            map = (HashMap<String, Object>) SafetyJudgment(sensor);
        }
        return map;
    }

    @Override
    public PageInfo<Sensor> selectSensorDataByTime(String minTime, String maxTime,Integer pageNum,Integer pageSize) {
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        PageHelper.startPage(pageNum,pageSize);
        List<Sensor> sensors = sensorMapper.selectSensorDataByTime(minDate, maxDate);
        PageInfo<Sensor> sensorPageInfo = new PageInfo<>(sensors);
        return sensorPageInfo;
    }

    @Override
    public Boolean insertAbnormal(Abnormal abnormal) {
        Integer affectRows = abnormalMapper.insertAbnormal(
                new Abnormal(abnormal.getStartTime() / 1000,
                        abnormal.getEndTime() / 1000,
                        abnormal.getRiskIndex()));
        return !(affectRows == 0);
    }

    @Override
    public Boolean updateAbnormal(Abnormal abnormal) {
        Integer affectRows = abnormalMapper.updateAbnormal(new Abnormal(
                abnormal.getStartTime() / 1000,
                abnormal.getEndTime() / 1000,
                abnormal.getRiskIndex()));
        return !(affectRows == 0);
    }

    @Override
    public List<Abnormal> clientDisconnectSelectAbnormalData(String closeTime, String startTime) {
        Timestamp closeDate = DateConverter.StringToTimeStamp(closeTime);
        Timestamp startDate = DateConverter.StringToTimeStamp(startTime);
        List<Abnormal> abnormals = abnormalMapper.restartSelectAbnormalData(closeDate, startDate);
        return abnormals;
    }

    @Override
    public List<Long> getStartTimeByRiskIndex(Integer riskIndex) {
        List<Long> startTimes = abnormalMapper.selectStartTimeByRiskIndex(riskIndex);
        return startTimes;
    }

    @Override
    public List<Double> getAbnormalGasData(String startTime) {
        String startDate = startTime + "000";
        List<Double> abnormalGasData = sensorMapper.selectAbnormalGasData(DateConverter.StringToTimeStamp(startDate));
        return abnormalGasData;
    }

    @Override
    public List<Double> getAbnormalSmogData(String startTime) {
        String startDate = startTime + "000";
        List<Double> abnormalSmogData = sensorMapper.selectAbnormalSmogData(DateConverter.StringToTimeStamp(startDate));
        return abnormalSmogData;
    }

    @Override
    public List<Double> getAbnormalTemperatureData(String startTime) {
        String startDate = startTime + "000";
        List<Double> abnormalTemperature = sensorMapper.selectAbnormalTemperatureData(DateConverter.StringToTimeStamp(startDate));
        return abnormalTemperature;
    }

    @Override
    public List<Double> getAbnormalHumidityData(String startTime) {
        String startDate = startTime + "000";
        List<Double> abnormalHumidityData = sensorMapper.selectAbnormalHumidityData(DateConverter.StringToTimeStamp(startDate));
        return abnormalHumidityData;
    }

    @Override
    public List<Double> getAbnormalShakeData(String startTime) {
        String startDate = startTime + "000";
        List<Double> abnormalShakeData = sensorMapper.selectAbnormalShakeData(DateConverter.StringToTimeStamp(startDate));
        return abnormalShakeData;
    }

    /**
     * @description: 判断是否安全并返回所需map，map中包含时间，传感器异常值，和异常系数
     * @author xiaoQe
     * @date 2022/12/14 21:16
     * @version 1.0
     */
    private Map<String,Object> SafetyJudgment(Sensor sensor) {
        HashMap<String, Object> warnMap = new HashMap<>();
        Integer riskIndex = 0;
        if(sensor.getGas() < 0.000 || sensor.getGas() > 0.01){
            warnMap.put("time", new Long(sensor.getTime()));
            warnMap.put("gas",sensor.getGas());
            riskIndex += RiskIndex.GAS_DANGER.getIndex();
        }
        if(sensor.getSmog() < 60 || sensor.getSmog() >70){
            warnMap.put("time", new Long(sensor.getTime()));
            warnMap.put("smog",sensor.getSmog());
            riskIndex += RiskIndex.SMOG_DANGER.getIndex();
        }
        if(sensor.getTemperature() > 29 || sensor.getTemperature() < 28){
            warnMap.put("time", new Long(sensor.getTime()));
            warnMap.put("temperature",sensor.getTemperature());
            riskIndex += RiskIndex.TEMPERATURE_DANGER.getIndex();
        }
        if(sensor.getHumidity() < 0.3 || sensor.getHumidity() > 0.6){
            warnMap.put("time", new Long(sensor.getTime()));
            warnMap.put("humidity",sensor.getHumidity());
            riskIndex += RiskIndex.HUMIDITY_DANGER.getIndex();
        }
        if (sensor.getShake() > 1){
            warnMap.put("time", new Long(sensor.getTime()));
            warnMap.put("shake",sensor.getShake());
            riskIndex += RiskIndex.SHAKE_DANGER.getIndex();
        }
        if(riskIndex != 0){
            warnMap.put("riskIndex",riskIndex);
        }
        return warnMap;
    }
}
