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

import java.sql.Date;
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
    public PageInfo<Map> getGasSensorData(String minTime, String maxTime, Integer pageNum, Integer pageSize,Character queryType){
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        Sensor gasSensor = new Sensor();
        gasSensor.setGas(1.0);
        PageHelper.startPage(pageNum,pageSize);
        List<Map> gasData = sensorMapper.selectSensorDataByQueryType(minDate,maxDate,gasSensor,queryType);
        PageInfo<Map> gasPageInfo = new PageInfo<>(gasData);
        return gasPageInfo;
    }

    @Override
    public PageInfo<Map> getSmogSensorData(String minTime, String maxTime,Integer pageNum,Integer pageSize,Character queryType){
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        Sensor smogSensor = new Sensor();
        smogSensor.setSmog(1.0);
        PageHelper.startPage(pageNum,pageSize);
        List<Map> smogSensorData = sensorMapper.selectSensorDataByQueryType(minDate, maxDate,smogSensor,queryType);
        PageInfo<Map> smogPageInfo = new PageInfo<>(smogSensorData);
        return smogPageInfo;
    }

    @Override
    public PageInfo<Map> getTemperatureSensorData(String minTime, String maxTime,Integer pageNum,Integer pageSize,Character queryType){
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        Sensor temperatureSensor = new Sensor();
        temperatureSensor.setTemperature(1.0);
        PageHelper.startPage(pageNum,pageSize);
        List<Map> temperatureSensorData = sensorMapper.selectSensorDataByQueryType(minDate,maxDate,temperatureSensor,queryType);
        PageInfo<Map> temperaturePageInfo = new PageInfo<>(temperatureSensorData);
        return temperaturePageInfo;
    }

    @Override
    public PageInfo<Map> getHumiditySensorData(String minTime, String maxTime,Integer pageNum,Integer pageSize,Character queryType) {
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        Sensor humiditySensor = new Sensor();
        humiditySensor.setHumidity(1.0);
        PageHelper.startPage(pageNum,pageSize);
        List<Map> humiditySensorData = sensorMapper.selectSensorDataByQueryType(minDate, maxDate,humiditySensor,queryType);
        PageInfo<Map> humidityPageInfo = new PageInfo<>(humiditySensorData);
        return humidityPageInfo;
    }


    @Override
    public PageInfo<Map> getShakeSensorData(String minTime, String maxTime,Integer pageNum,Integer pageSize,Character queryType){
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        Sensor shakeSensor = new Sensor();
        shakeSensor.setShake(1.0);
        PageHelper.startPage(pageNum,pageSize);
        List<Map> shakeSensorData = sensorMapper.selectSensorDataByQueryType(minDate, maxDate,shakeSensor,queryType);
        PageInfo<Map> shakePageInfo = new PageInfo<>(shakeSensorData);
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
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateAbnormal(Abnormal abnormal) {
        Integer affectRows = abnormalMapper.updateAbnormal(new Abnormal(
                abnormal.getStartTime() / 1000,
                abnormal.getEndTime() / 1000,
                abnormal.getRiskIndex()));
        if(affectRows == 0){
            return false;
        }
        return true;
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
    public PageInfo<Map> getAbnormalGasData(String startTime,Integer pageNum,Integer pageSize,Character queryType) {
        String startDate = startTime + "000";
        Timestamp startTimestamp = DateConverter.StringToTimeStamp(startDate);
        Sensor abnormalGasSensor = new Sensor();
        abnormalGasSensor.setGas(1.0);
        PageHelper.startPage(pageNum,pageSize);
        List<Map> abnormalGasData = sensorMapper.selectAbnormalSensorData(abnormalGasSensor,startTimestamp,queryType);
        PageInfo<Map> abnormalGasPageInfo = new PageInfo<>(abnormalGasData);
        return abnormalGasPageInfo;
    }

    @Override
    public PageInfo<Map> getAbnormalSmogData(String startTime,Integer pageNum,Integer pageSize,Character queryType) {
        String startDate = startTime + "000";
        Timestamp startTimestamp = DateConverter.StringToTimeStamp(startDate);
        Sensor abnormalSmogSensor = new Sensor();
        abnormalSmogSensor.setSmog(1.0);
        PageHelper.startPage(pageNum,pageSize);
        List<Map> abnormalSmogData = sensorMapper.selectAbnormalSensorData(abnormalSmogSensor,startTimestamp,queryType);
        PageInfo<Map> abnormalSmogPageInfo = new PageInfo<>(abnormalSmogData);
        return abnormalSmogPageInfo;
    }

    @Override
    public PageInfo<Map> getAbnormalTemperatureData(String startTime,Integer pageNum,Integer pageSize,Character queryType) {
        String startDate = startTime + "000";
        Timestamp startTimestamp = DateConverter.StringToTimeStamp(startDate);
        Sensor abnormalTemperatureSensor = new Sensor();
        abnormalTemperatureSensor.setTemperature(1.0);
        PageHelper.startPage(pageNum,pageSize);
        List<Map> abnormalTemperature = sensorMapper.selectAbnormalSensorData(abnormalTemperatureSensor,startTimestamp,queryType);
        PageInfo<Map> abnormalTemperaturePageInfo = new PageInfo<>(abnormalTemperature);
        return abnormalTemperaturePageInfo;
    }

    @Override
    public PageInfo<Map> getAbnormalHumidityData(String startTime,Integer pageNum,Integer pageSize,Character queryType) {
        String startDate = startTime + "000";
        Timestamp startTimestamp = DateConverter.StringToTimeStamp(startDate);
        Sensor abnormalHumiditySensor = new Sensor();
        abnormalHumiditySensor.setHumidity(1.0);
        PageHelper.startPage(pageNum,pageSize);
        List<Map> abnormalHumidityData = sensorMapper.selectAbnormalSensorData(abnormalHumiditySensor,startTimestamp,queryType);
        PageInfo<Map> abnormalHumidityPageInfo = new PageInfo<>(abnormalHumidityData);
        return abnormalHumidityPageInfo;
    }

    @Override
    public PageInfo<Map> getAbnormalShakeData(String startTime,Integer pageNum,Integer pageSize,Character queryType) {
        String startDate = startTime + "000";
        Timestamp startTimestamp = DateConverter.StringToTimeStamp(startDate);
        Sensor abnormalShakeSensor = new Sensor();
        abnormalShakeSensor.setShake(1.0);
        PageHelper.startPage(pageNum,pageSize);
        List<Map> abnormalShakeData = sensorMapper.selectAbnormalSensorData(abnormalShakeSensor,startTimestamp,queryType);
        PageInfo<Map> abnormalShakePageInfo = new PageInfo<>(abnormalShakeData);
        return abnormalShakePageInfo;
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
