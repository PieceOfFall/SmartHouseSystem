package com.fall.smarthouse;

import com.fall.smarthouse.constant.LightState;
import com.fall.smarthouse.mapper.AbnormalMapper;
import com.fall.smarthouse.mapper.ElectricMapper;
import com.fall.smarthouse.mapper.SensorMapper;
import com.fall.smarthouse.mapper.UserMapper;
import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.model.ReturnHistory;
import com.fall.smarthouse.model.Sensor;
import com.fall.smarthouse.model.User;
import com.fall.smarthouse.service.IElectricApplianceService;
import com.fall.smarthouse.service.ISensorService;
import com.fall.smarthouse.service.ISmartService;
import com.fall.smarthouse.service.IUserService;
import com.fall.smarthouse.service.impl.SensorServiceImpl;
import com.fall.smarthouse.util.DateConverter;
import com.fall.smarthouse.util.JWTUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    IUserService userService;
    @Autowired
    ISmartService smartService;

    @Value("${spring.mail.username}")
    String fromMail;
    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    AbnormalMapper abnormalMapper;
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
    @Autowired
    SensorServiceImpl sensorServiceI;


    @Test
    void testInsertUser(){
        User user = new User("123456678", "123456", new Long("1673009586"), "123456@qq.com", 1);
        userService.addUser("123456789",user);
    }

    @Test
    void testUser(){
        Integer integer = userMapper.selectRoleRoot();
        System.out.println(integer);
    }
    @Test
    void getEmail(){
        System.out.println(userMapper.selectAllEmail());
    }
    @Test
    void testMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("警告");
        message.setText("家中出现破门情况，请注意");
        message.setTo("2770838499@qq.com");
        message.setFrom("2770838499@qq.com");
        mailSender.send(message);
    }
    @Test
    void testAddHistory(){
        ElectricAppliance electricAppliance = new ElectricAppliance();
        electricAppliance.setLightLivingRoom(3);
        electricAppliance.setCurtainB(0);
        electricApplianceService.addElectricHistory("xiaoQe",electricAppliance);
    }

    @Test
    void testMap(){
        List<Map<String, Object>> maps = sensorMapper.testMap();
        System.out.println(maps);
    }
    @Test
    void BitwiseOperation(){
        PageInfo<ReturnHistory> account = electricApplianceService.getHistory("account", "1671784726000", 1, 3);
        System.out.println(account);
//        System.out.println(LightState.CLOSED);
//        String s = "lig ht";
//        String a = s + 'A';
//        System.out.println(a);
//        Integer i1 = 9;
//        Integer i2 = 2;
//        System.out.println(i1 & i2);
//        PageInfo<Map> doubles = sensorService.getAbnormalTemperatureData("1671032420",1,7,'s');
//        System.out.println(doubles);
    }

    @Test
    void selectAbnormal(){
//        List<Abnormal> abnormals = abnormalService.restartSelectAbnormalData("1671018424000", "1671029264000");
//        System.out.println(abnormals);
        HashSet<Object> integers = new HashSet<>();
        System.out.println(integers);
        integers.add(new Long("12345678"));
        integers.add(12);
        Iterator<Object> iterator = integers.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            if(obj.getClass().equals(Integer.class)){
                System.out.println(obj.getClass().equals(Integer.class));
            }else if(obj.getClass().equals(Long.class)){
                System.out.println(obj.getClass().equals(Long.class));
            }
        }
//        System.out.println(integers);
//        integers.remove(next);
//        System.out.println(integers);
    }

    @Test
    void testInsertAbnormal(){
//        Abnormal abnormal = new Abnormal();
//        abnormal.setStartTime(new Long("1671019424000"));
//        abnormal.setEndTime(new Long("1671019424000"));
//        abnormal.setRiskIndex(RiskIndex.GAS_DANGER.getIndex());
////        Integer integer = abnormalMapper.insertAbnormal(new Abnormal(abnormal.getStartTime() / 1000, abnormal.getEndTime() / 1000, abnormal.getRiskIndex()));
//        abnormal.setEndTime(new Long("1671029264000"));
//        Integer integer = abnormalMapper.updateAbnormal(new Abnormal(
//                abnormal.getStartTime()/1000, abnormal.getEndTime()/1000, abnormal.getRiskIndex()
//        ));
//        System.out.println(integer);
    }

    @Test
    void testDateTime() throws ParseException {
        System.out.println(DateConverter.StringToTimeStamp("1670935260000"));
    }

    @Test
    void testSelectAll(){
        List<Sensor> sensorList = sensorMapper.pollingSelectSensorData(DateConverter.StringToTimeStamp("1670942460000"));
        System.out.println(sensorList);
    }


    @Test
    void testPage() throws ParseException {
//        PageHelper.startPage(1,3);
        PageInfo<Map> shakeSensorData = sensorServiceI.getHumiditySensorData("1670942407000", "1671032560000", 1, 10,'m');
//        System.out.println(data);
//        System.out.println();
//        PageInfo<Double> doublePageInfo = new PageInfo<>(data,3);
//        Timestamp minDate = DateConverter.StringToTimeStamp("1670942400000");
//        Timestamp maxDate = DateConverter.StringToTimeStamp("1670942460000");
//        List<Double> doubles = sensorMapper.selectGasSensorData(minDate, maxDate);
        System.out.println(shakeSensorData);
    }
    @Test
    void testSensorService() throws ParseException {
//        System.out.println(sensorService.getGasSensorData("11111111111","1770231713291",1,2));
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
        String ret = smartService.userLogin("940313262", "123456");
        System.out.println(ret);
    }

    @Test
    void testAssert(){
        Integer a = null;
        assert a !=null;
        System.out.println(a.equals(1));
    }


}
