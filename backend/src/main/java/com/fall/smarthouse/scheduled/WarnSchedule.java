package com.fall.smarthouse.scheduled;

import com.fall.smarthouse.constant.SwitchState;
import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.service.IElectricApplianceService;
import com.fall.smarthouse.service.IUserService;
import com.fall.smarthouse.service.impl.SensorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/28 19:16
 */
@Component
@EnableScheduling
@EnableAsync
public class WarnSchedule {
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    IUserService userService;
    @Autowired
    IElectricApplianceService electricApplianceService;
    @Autowired
    JavaMailSenderImpl mailSender;

    private Integer sendTimes = 0;

    @Async
    @Scheduled(fixedDelay = 5000)
    public void sendWarnEmail(){
        if(SensorServiceImpl.abnormalType != null){
            Long nowTime = Calendar.getInstance().getTimeInMillis();
            if(sendTimes.equals(0)){
                List<String> emails = userService.getAllEmail();
                for (String email : emails) {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setSubject("传感器异常警告");
                    message.setText("传感器数据异常，请尽快查看并检修");
                    message.setFrom(fromEmail);
                    message.setTo(email);
                    mailSender.send(message);
                }
                sendTimes++;
                ElectricAppliance warnLight = new ElectricAppliance();
                warnLight.setWarnLight(SwitchState.ON.getState());
                electricApplianceService.setWarnLight(warnLight);
            }else {
                Long timeDifference = nowTime - SensorServiceImpl.startTime;
                Long aLong = new Long(180000 * sendTimes);
                if(timeDifference > aLong) {
                    List<String> emails = userService.getAllEmail();
                    for (String email : emails) {
                        SimpleMailMessage message = new SimpleMailMessage();
                        message.setSubject("传感器异常警告");
                        message.setText("传感器数据异常，请尽快查看并检修");
                        message.setFrom(fromEmail);
                        message.setTo(email);
                        mailSender.send(message);
                    }
                    sendTimes++;
                }
            }
        }else if (SensorServiceImpl.abnormalType == null){
            if(!sendTimes.equals(0)){
                sendTimes = 0;
                ElectricAppliance warnLight = new ElectricAppliance();
                warnLight.setWarnLight(SwitchState.OFF.getState());
                electricApplianceService.setWarnLight(warnLight);
            }
        }
    }
}
