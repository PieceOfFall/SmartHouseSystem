package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.mapper.SensorMapper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FAll
 * @date 2022/12/2 17:21
 */
@Service
public class SensorServiceImpl implements ISensorService {

    @Autowired
    SensorMapper sensorMapper;

    @Override
    public boolean insertToSensor(Sensor sensorRequest) throws ParseException {
        Sensor sensor = new Sensor(sensorRequest.getTime()/1000, sensorRequest.getGas(), sensorRequest.getSmog(),
                sensorRequest.getTemperature(),sensorRequest.getHumidity() , sensorRequest.getShake());
        Integer affectRows = sensorMapper.insertToSensor(sensor);
        if(affectRows == 0){
            return false;
        }
        return true;
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

        return null;
    }


    private Map<String,Object> SafetyJudgment(Sensor sensor,Map<String,Object> warnMap) {
        if(sensor.getGas() < 0.001 || sensor.getGas() > 0.01){
            warnMap.put("time", new Long(sensor.getTime()));
            warnMap.put("gas",sensor.getGas());
        }
        if(sensor.getSmog() < 60 || sensor.getSmog() >70){
            warnMap.put("time", new Long(sensor.getTime()));
            warnMap.put("smog",sensor.getSmog());
        }
        if(sensor.getTemperature() > 29 || sensor.getTemperature() < 28){
            warnMap.put("time", new Long(sensor.getTime()));
            warnMap.put("temperature",sensor.getTemperature());
        }
        if(sensor.getHumidity() < 0.3 || sensor.getHumidity() > 0.6){
            warnMap.put("time", new Long(sensor.getTime()));
            warnMap.put("humidity",sensor.getHumidity());
        }
        if (sensor.getShake() > 1){
            warnMap.put("time", new Long(sensor.getTime()));
            warnMap.put("shake",sensor.getShake());
        }
        return warnMap;
    }
}
