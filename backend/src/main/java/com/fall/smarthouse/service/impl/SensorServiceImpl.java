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
import java.util.*;

/**
 * @author FAll
 * @date 2022/12/2 17:21
 */
@Service
public class SensorServiceImpl implements ISensorService {

    private final AbnormalMapper abnormalMapper;

    private final SensorMapper sensorMapper;

    @Autowired
    public SensorServiceImpl(AbnormalMapper abnormalMapper, SensorMapper sensorMapper) {
        this.abnormalMapper = abnormalMapper;
        this.sensorMapper = sensorMapper;
    }

    public static Integer abnormalType = null;
    public static Long startTime = null;

    @Override
    public Integer isAbnormalExist() {
        return abnormalType;
    }

    @Override
    public void insertToSensor(Sensor sensorRequest) {
        Map<String, Object> map = SafetyJudgment(sensorRequest);
        if (!map.isEmpty() && abnormalType == null) {
            Integer riskIndex = (Integer) map.get("riskIndex");
            // 将数据异常信息添加到异常表中
            insertAbnormal(new Abnormal(sensorRequest.getTime(), sensorRequest.getTime(), riskIndex));
            // 将异常系数及开始时间存入hashSet
            abnormalType = riskIndex;
            startTime = sensorRequest.getTime();
        } else if (!map.isEmpty()) {
            Integer riskIndexForMap = (Integer) map.get("riskIndex");
            // 比较异常系数是否相等
            if (!Objects.equals(riskIndexForMap, abnormalType)) {
                insertAbnormal(new Abnormal(sensorRequest.getTime(),
                        sensorRequest.getTime(), riskIndexForMap));
                abnormalType = riskIndexForMap;
                startTime = sensorRequest.getTime();
            } else {
                updateAbnormal(new Abnormal(startTime, sensorRequest.getTime(), abnormalType));
            }

        } else if (abnormalType != null) {
            abnormalType = null;
            startTime = null;
        }
        Sensor sensor = new Sensor(sensorRequest.getTime() / 1000, sensorRequest.getGas(), sensorRequest.getSmog(),
                sensorRequest.getTemperature(), sensorRequest.getHumidity(), sensorRequest.getShake());
        sensorMapper.insertToSensor(sensor);
    }

    @Override
    public PageInfo<Map<String, Object>> getGasSensorData(String minTime, String maxTime, Integer pageNum, Integer pageSize, Character queryType) {
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        Sensor gasSensor = new Sensor();
        gasSensor.setGas(1.0);
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> gasData = sensorMapper.selectSensorDataByQueryType(minDate, maxDate, gasSensor, queryType);
        return new PageInfo<>(gasData);
    }

    @Override
    public PageInfo<Map<String, Object>> getSmogSensorData(String minTime, String maxTime, Integer pageNum, Integer pageSize, Character queryType) {
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        Sensor smogSensor = new Sensor();
        smogSensor.setSmog(1.0);
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> smogSensorData = sensorMapper.selectSensorDataByQueryType(minDate, maxDate, smogSensor, queryType);
        return new PageInfo<>(smogSensorData);
    }

    @Override
    public PageInfo<Map<String, Object>> getTemperatureSensorData(String minTime, String maxTime, Integer pageNum, Integer pageSize, Character queryType) {
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        Sensor temperatureSensor = new Sensor();
        temperatureSensor.setTemperature(1.0);
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> temperatureSensorData = sensorMapper.selectSensorDataByQueryType(minDate, maxDate, temperatureSensor, queryType);
        return new PageInfo<>(temperatureSensorData);
    }

    @Override
    public PageInfo<Map<String, Object>> getHumiditySensorData(String minTime, String maxTime, Integer pageNum, Integer pageSize, Character queryType) {
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        Sensor humiditySensor = new Sensor();
        humiditySensor.setHumidity(1.0);
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> humiditySensorData = sensorMapper.selectSensorDataByQueryType(minDate, maxDate, humiditySensor, queryType);
        return new PageInfo<>(humiditySensorData);
    }


    @Override
    public PageInfo<Map<String, Object>> getShakeSensorData(String minTime, String maxTime, Integer pageNum, Integer pageSize, Character queryType) {
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        Sensor shakeSensor = new Sensor();
        shakeSensor.setShake(1.0);
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> shakeSensorData = sensorMapper.selectSensorDataByQueryType(minDate, maxDate, shakeSensor, queryType);
        return new PageInfo<>(shakeSensorData);
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
    public PageInfo<Sensor> selectSensorDataByTime(String minTime, String maxTime, Integer pageNum, Integer pageSize) {
        Timestamp minDate = DateConverter.StringToTimeStamp(minTime);
        Timestamp maxDate = DateConverter.StringToTimeStamp(maxTime);
        PageHelper.startPage(pageNum, pageSize);
        List<Sensor> sensors = sensorMapper.selectSensorDataByTime(minDate, maxDate);
        return new PageInfo<>(sensors);
    }

    @Override
    public void insertAbnormal(Abnormal abnormal) {
        abnormalMapper.insertAbnormal(
                new Abnormal(abnormal.getStartTime() / 1000,
                        abnormal.getEndTime() / 1000,
                        abnormal.getRiskIndex()));
    }

    @Override
    public void updateAbnormal(Abnormal abnormal) {
        abnormalMapper.updateAbnormal(new Abnormal(
                abnormal.getStartTime() / 1000,
                abnormal.getEndTime() / 1000,
                abnormal.getRiskIndex()));
    }

    @Override
    public List<Abnormal> clientDisconnectSelectAbnormalData(String closeTime, String startTime) {
        Timestamp closeDate = DateConverter.StringToTimeStamp(closeTime);
        Timestamp startDate = DateConverter.StringToTimeStamp(startTime);
        return abnormalMapper.restartSelectAbnormalData(closeDate, startDate);
    }

    @Override
    public List<Long> getStartTimeByRiskIndex(Integer riskIndex) {
        return abnormalMapper.selectStartTimeByRiskIndex(riskIndex);
    }

    @Override
    public PageInfo<Map<String, Object>> getAbnormalGasData(String startTime, Integer pageNum, Integer pageSize, Character queryType) {
        String startDate = startTime + "000";
        Timestamp startTimestamp = DateConverter.StringToTimeStamp(startDate);
        Sensor abnormalGasSensor = new Sensor();
        abnormalGasSensor.setGas(1.0);
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> abnormalGasData = sensorMapper.selectAbnormalSensorData(abnormalGasSensor, startTimestamp, queryType);
        return new PageInfo<>(abnormalGasData);
    }

    @Override
    public PageInfo<Map<String, Object>> getAbnormalSmogData(String startTime, Integer pageNum, Integer pageSize, Character queryType) {
        String startDate = startTime + "000";
        Timestamp startTimestamp = DateConverter.StringToTimeStamp(startDate);
        Sensor abnormalSmogSensor = new Sensor();
        abnormalSmogSensor.setSmog(1.0);
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> abnormalSmogData = sensorMapper.selectAbnormalSensorData(abnormalSmogSensor, startTimestamp, queryType);
        return new PageInfo<>(abnormalSmogData);
    }

    @Override
    public PageInfo<Map<String, Object>> getAbnormalTemperatureData(String startTime, Integer pageNum, Integer pageSize, Character queryType) {
        String startDate = startTime + "000";
        Timestamp startTimestamp = DateConverter.StringToTimeStamp(startDate);
        Sensor abnormalTemperatureSensor = new Sensor();
        abnormalTemperatureSensor.setTemperature(1.0);
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> abnormalTemperature = sensorMapper.selectAbnormalSensorData(abnormalTemperatureSensor, startTimestamp, queryType);
        return new PageInfo<>(abnormalTemperature);
    }

    @Override
    public PageInfo<Map<String, Object>> getAbnormalHumidityData(String startTime, Integer pageNum, Integer pageSize, Character queryType) {
        String startDate = startTime + "000";
        Timestamp startTimestamp = DateConverter.StringToTimeStamp(startDate);
        Sensor abnormalHumiditySensor = new Sensor();
        abnormalHumiditySensor.setHumidity(1.0);
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> abnormalHumidityData = sensorMapper.selectAbnormalSensorData(abnormalHumiditySensor, startTimestamp, queryType);
        return new PageInfo<>(abnormalHumidityData);
    }

    @Override
    public PageInfo<Map<String, Object>> getAbnormalShakeData(String startTime, Integer pageNum, Integer pageSize, Character queryType) {
        String startDate = startTime + "000";
        Timestamp startTimestamp = DateConverter.StringToTimeStamp(startDate);
        Sensor abnormalShakeSensor = new Sensor();
        abnormalShakeSensor.setShake(1.0);
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> abnormalShakeData = sensorMapper.selectAbnormalSensorData(abnormalShakeSensor, startTimestamp, queryType);
        return new PageInfo<>(abnormalShakeData);
    }

    /**
     * @description: 判断是否安全并返回所需map，map中包含时间，传感器异常值，和异常系数
     * @author xiaoQe
     * @date 2022/12/14 21:16
     */
    private Map<String, Object> SafetyJudgment(Sensor sensor) {
        HashMap<String, Object> warnMap = new HashMap<>();
        int riskIndex = 0;
        if (sensor.getGas() < 0.000 || sensor.getGas() > 0.01) {
            warnMap.put("time", sensor.getTime());
            warnMap.put("gas", sensor.getGas());
            riskIndex += RiskIndex.GAS_DANGER.getIndex();
        }
        if (sensor.getSmog() < 60 || sensor.getSmog() > 70) {
            warnMap.put("time", sensor.getTime());
            warnMap.put("smog", sensor.getSmog());
            riskIndex += RiskIndex.SMOG_DANGER.getIndex();
        }
        if (sensor.getTemperature() > 29 || sensor.getTemperature() < 28) {
            warnMap.put("time", sensor.getTime());
            warnMap.put("temperature", sensor.getTemperature());
            riskIndex += RiskIndex.TEMPERATURE_DANGER.getIndex();
        }
        if (sensor.getHumidity() < 0.3 || sensor.getHumidity() > 0.6) {
            warnMap.put("time", sensor.getTime());
            warnMap.put("humidity", sensor.getHumidity());
            riskIndex += RiskIndex.HUMIDITY_DANGER.getIndex();
        }
        if (sensor.getShake() > 1) {
            warnMap.put("time", sensor.getTime());
            warnMap.put("shake", sensor.getShake());
            riskIndex += RiskIndex.SHAKE_DANGER.getIndex();
        }
        if (riskIndex != 0) {
            warnMap.put("riskIndex", riskIndex);
        }
        return warnMap;
    }
}
