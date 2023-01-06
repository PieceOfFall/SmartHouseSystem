package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.bean.MenuItem;
import com.fall.smarthouse.constant.MenuID;
import com.fall.smarthouse.mapper.UserMapper;
import com.fall.smarthouse.service.IUserService;
import com.fall.smarthouse.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author FAll
 * @date 2022/12/2 17:21
 */
@Service
public class UserServiceImpl implements IUserService {

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    UserMapper userMapper;

    @Override
    public Long getCreatTime(String account) {
        Long creatTime = userMapper.selectCreatTime(account);
        return creatTime;
    }

    @Override
    public void sendEmail(String subject,String text,String account) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(fromEmail);
        String email = userMapper.selectEmail(account);
        message.setTo(email);
        mailSender.send(message);
    }


    @Override
    public List<String> getAllEmail() {
        return userMapper.selectAllEmail();
    }

}
