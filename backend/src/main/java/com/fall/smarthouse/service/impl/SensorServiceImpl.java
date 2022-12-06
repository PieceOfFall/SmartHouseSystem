package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.mapper.SensorMapper;
import com.fall.smarthouse.model.Sensor;
import com.fall.smarthouse.service.ISensorService;
import com.fall.smarthouse.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
    public List<Double> getGasSensorData(String minTime, String maxTime) throws ParseException {
        Date minDate = DateConverter.StringToSqlDate(minTime);
        Date maxDate = DateConverter.StringToSqlDate(maxTime);
        List<Double> gasData = sensorMapper.selectGasSensorData(minDate, maxDate);
        return gasData;
    }

    @Override
    public List<Double> getSmogSensorData(String minTime, String maxTime) throws ParseException {
        Date minDate = DateConverter.StringToSqlDate(minTime);
        Date maxDate = DateConverter.StringToSqlDate(maxTime);
        List<Double> smogSensorData = sensorMapper.selectSmogSensorData(minDate, maxDate);
        return smogSensorData;
    }

    @Override
    public List<Double> getTemperatureSensorData(String minTime, String maxTime) throws ParseException {
        Date minDate = DateConverter.StringToSqlDate(minTime);
        Date maxDate = DateConverter.StringToSqlDate(maxTime);
        List<Double> temperatureSensorData = sensorMapper.selectTemperatureSensorData(minDate, maxDate);
        return temperatureSensorData;
    }

    @Override
    public List<Double> getHumiditySensorData(String minTime, String maxTime) throws ParseException {
        Date minDate = DateConverter.StringToSqlDate(minTime);
        Date maxDate = DateConverter.StringToSqlDate(maxTime);
        List<Double> humiditySensorData = sensorMapper.selectHumiditySensorData(minDate, maxDate);
        return humiditySensorData;
    }

    @Override
    public List<Double> getShakeSensorData(String minTime, String maxTime) throws ParseException {
        Date minDate = DateConverter.StringToSqlDate(minTime);
        Date maxDate = DateConverter.StringToSqlDate(maxTime);
        List<Double> shakeSensorData = sensorMapper.selectShakeSensorData(minDate, maxDate);
        return shakeSensorData;
    }
}
