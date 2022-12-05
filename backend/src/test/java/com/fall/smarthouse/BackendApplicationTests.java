package com.fall.smarthouse;

import com.fall.smarthouse.mapper.ElectricMapper;
import com.fall.smarthouse.mapper.SensorMapper;
import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.model.Sensor;
import com.fall.smarthouse.service.IElectricApplianceService;
import com.fall.smarthouse.service.ISensorService;
import com.fall.smarthouse.service.impl.ElectricApplianceServiceImpl;
import com.fall.smarthouse.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    SensorMapper sensorMapper;

    @Autowired
    ElectricMapper electricMapper;

    @Autowired
    IElectricApplianceService electricApplianceService;

    @Autowired
    ISensorService sensorService;

    @Test
    void testSensorService() throws ParseException {
        System.out.println(sensorService.getGasSensorData("11111111111","1770231713291"));
//        System.out.println(sensorService.insertToSensor("1770231713291",3.0,4.9,7.0,7.8,2.1));
    }


    @Test
    void testUpdateElectricAppliance(){
        System.out.println(System.currentTimeMillis());
        System.out.println(electricApplianceService.setWarnLight(0));
    }

    @Test
    void testElectric(){
        System.out.println(electricApplianceService.getWarnLight());
    }

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

    @Test
    void testJwtCreate(){
        String token = JWTUtil.createToken("张三");
        System.out.println(token);
    }

    @Test
    void testJwtDecode() throws Exception {
        String s = JWTUtil.validateToken("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiLlvKDkuIkiLCJleHAiOjE2NzAyOTY3NDR9.w5WOPs5_GVRhzSAkBT2BrVGF60j34_YJRVbUbLnkwZwTvFBKFvv5l_1w4VRuqiZVYVJdHH2kKCQrdkRl2tzafQ");
    }

    @Test
    void testIsNeedUpdate() throws Exception {
        boolean ret = JWTUtil.isNeedUpdate("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiLlvKDkuIkiLCJleHAiOjE2NzAyOTY3NDR9.w5WOPs5_GVRhzSAkBT2BrVGF60j34_YJRVbUbLnkwZwTvFBKFvv5l_1w4VRuqiZVYVJdHH2kKCQrdkRl2tzafQ");
        System.out.println(ret);
    }

    @Test
    void testSimpleDateFormat() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date(System.currentTimeMillis()));
        System.out.println(format);
    }



}
