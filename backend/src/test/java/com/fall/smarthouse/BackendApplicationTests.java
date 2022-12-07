package com.fall.smarthouse;

import com.fall.smarthouse.mapper.ElectricMapper;
import com.fall.smarthouse.mapper.SensorMapper;
import com.fall.smarthouse.mapper.UserMapper;
import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.model.Sensor;
import com.fall.smarthouse.model.User;
import com.fall.smarthouse.service.IElectricApplianceService;
import com.fall.smarthouse.service.ISensorService;
import com.fall.smarthouse.service.impl.ElectricApplianceServiceImpl;
import com.fall.smarthouse.util.JWTUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

    @Autowired
    UserMapper userMapper;


    @Test
    void testPage() throws ParseException {
        PageHelper.startPage(1,3);
        PageInfo<Double> shakeSensorData = sensorService.getShakeSensorData("11111111111", "1770231713291", 1, 2);
//        System.out.println(data);
//        System.out.println();
//        PageInfo<Double> doublePageInfo = new PageInfo<>(data,3);
        System.out.println(shakeSensorData);
    }
    @Test
    void testSensorService() throws ParseException {
        System.out.println(sensorService.getGasSensorData("11111111111","1770231713291",1,2));
//        System.out.println(sensorService.insertToSensor("1770231713291",3.0,4.9,7.0,7.8,2.1));
    }

    @Test
    void testJackson() throws JsonProcessingException {
        User user = new User();
        user.setAccount("123");
        user.setPassword("456");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(user));
    }

    @Test
    void testUpdateElectricAppliance(){
        ElectricAppliance electricAppliance = new ElectricAppliance();
        electricAppliance.setLightBedB(0);
        System.out.println(electricApplianceService.setLightBedB(electricAppliance));
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

    @Test
    void testLogin(){
        String ret = userMapper.userLogin("940313262", "123456");
        System.out.println(ret);
    }


}
