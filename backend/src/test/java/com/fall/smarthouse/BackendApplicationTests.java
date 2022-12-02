package com.fall.smarthouse;

import com.fall.smarthouse.mapper.ElectricMapper;
import com.fall.smarthouse.mapper.SensorMapper;
import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.model.Sensor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Calendar;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    SensorMapper sensorMapper;

    @Autowired
    ElectricMapper electricMapper;

    @Test
    void testInsert() {
        sensorMapper.testAdd(new Timestamp(Calendar.getInstance().getTimeInMillis()));

    }

    @Test
    void testSelectByXml() {
        Sensor sensor = sensorMapper.testMapper();
        System.out.println(sensor);
    }

    @Test
    void testGetElectric(){
        ElectricAppliance appliance = electricMapper.getAppliance();
        System.out.println(appliance);
    }

}
