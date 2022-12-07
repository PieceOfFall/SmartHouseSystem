package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.mapper.SensorMapper;
import com.fall.smarthouse.model.Sensor;
import com.fall.smarthouse.service.ISensorService;
import com.fall.smarthouse.util.DateConverter;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public boolean insertToSensor(String time, Double gas, Double smog, Double temperature,
                                  Double humidity, Double shake) throws ParseException {
        String dateString = DateConverter.LongToDateString(Long.parseLong(time));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(sdf.parse(dateString).getTime());
        Sensor sensor = new Sensor(date, gas, smog, temperature, humidity, shake);
        Integer affectRows = sensorMapper.insertToSensor(sensor);
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    @Override
    public PageInfo<Double> getGasSensorData(String minTime, String maxTime, Integer pageNum, Integer pageSize) throws ParseException {
        Date minDate = DateConverter.StringToSqlDate(minTime);
        Date maxDate = DateConverter.StringToSqlDate(maxTime);
        PageHelper.startPage(pageNum,pageSize);
        List<Double> gasData = sensorMapper.selectGasSensorData(minDate, maxDate);
        PageInfo<Double> gasPageInfo = new PageInfo<>(gasData);
        return gasPageInfo;
    }

    @Override
    public PageInfo<Double> getSmogSensorData(String minTime, String maxTime,Integer pageNum,Integer pageSize) throws ParseException {
        Date minDate = DateConverter.StringToSqlDate(minTime);
        Date maxDate = DateConverter.StringToSqlDate(maxTime);
        PageHelper.startPage(pageNum,pageSize);
        List<Double> smogSensorData = sensorMapper.selectSmogSensorData(minDate, maxDate);
        PageInfo<Double> smogPageInfo = new PageInfo<>(smogSensorData);
        return smogPageInfo;
    }

    @Override
    public PageInfo<Double> getTemperatureSensorData(String minTime, String maxTime,Integer pageNum,Integer pageSize) throws ParseException {
        Date minDate = DateConverter.StringToSqlDate(minTime);
        Date maxDate = DateConverter.StringToSqlDate(maxTime);
        PageHelper.startPage(pageNum,pageSize);
        List<Double> temperatureSensorData = sensorMapper.selectTemperatureSensorData(minDate, maxDate);
        PageInfo<Double> temperaturePageInfo = new PageInfo<>(temperatureSensorData);
        return temperaturePageInfo;
    }

    @Override
    public PageInfo<Double> getHumiditySensorData(String minTime, String maxTime,Integer pageNum,Integer pageSize) throws ParseException {
        Date minDate = DateConverter.StringToSqlDate(minTime);
        Date maxDate = DateConverter.StringToSqlDate(maxTime);
        PageHelper.startPage(pageNum,pageSize);
        List<Double> humiditySensorData = sensorMapper.selectHumiditySensorData(minDate, maxDate);
        PageInfo<Double> humidityPageInfo = new PageInfo<>(humiditySensorData);
        return humidityPageInfo;
    }

    @Override
    public PageInfo<Double> getShakeSensorData(String minTime, String maxTime,Integer pageNum,Integer pageSize) throws ParseException {
        Date minDate = DateConverter.StringToSqlDate(minTime);
        Date maxDate = DateConverter.StringToSqlDate(maxTime);
        PageHelper.startPage(pageNum,pageSize);
        List<Double> shakeSensorData = sensorMapper.selectShakeSensorData(minDate, maxDate);
        PageInfo<Double> shakePageInfo = new PageInfo<>(shakeSensorData);
        return shakePageInfo;
    }

    @Override
    public Map<String,Object> safetyInspection() {
        Sensor sensor = sensorMapper.selectLastTimeSensorData();
        Map<String, Object> warnMap = new HashMap<>();
        if(sensor.getGas() < 2 || sensor.getGas() > 15){
            warnMap.put("time", new Long(sensor.getTime().getTime()));
            warnMap.put("gas",sensor.getGas());
        }
        if(sensor.getSmog() < 5 || sensor.getSmog() >25){
            warnMap.put("time", new Long(sensor.getTime().getTime()));
            warnMap.put("smog",sensor.getSmog());
        }
        if(sensor.getTemperature() > 57){
            warnMap.put("time", new Long(sensor.getTime().getTime()));
            warnMap.put("temperature",sensor.getTemperature());
        }
        if(sensor.getHumidity() < 20 || sensor.getHumidity() > 80){
            warnMap.put("time", new Long(sensor.getTime().getTime()));
            warnMap.put("humidity",sensor.getHumidity());
        }
        if (sensor.getShake() > 30){
            warnMap.put("time", new Long(sensor.getTime().getTime()));
            warnMap.put("shake",sensor.getShake());
        }
        return warnMap;
    }
}
