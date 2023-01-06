package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.bean.MenuItem;
import com.fall.smarthouse.constant.MenuID;
import com.fall.smarthouse.constant.UserRole;
import com.fall.smarthouse.mapper.UserMapper;
import com.fall.smarthouse.model.User;
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

    @Override
    public List<User> getAllUser() {
        List<User> users = userMapper.selectAllUser();
        return users;
    }

    @Override
    public Boolean addUser(String account, User user) {
        Integer accountRole = userMapper.selectRoleByAccount(account);
        user.setCreatTime(user.getCreatTime()/1000);
        Integer affectRows;
        if(accountRole > user.getRole()){
            affectRows = userMapper.insertUser(user);
        }else {
            affectRows = 0;
        }
        return affectRows != 0;
    }

    @Override
    public Boolean deleteUser(String account, User user) {
        Integer accountRole = userMapper.selectRoleByAccount(account);
        Integer affectRows;
        if(accountRole > user.getRole()){
            affectRows = userMapper.deleteUser(user);
        }else {
            affectRows = 0;
        }
        return affectRows != 0;
    }

    @Override
    public Boolean updateUser(String account, User user) {
        Integer accountRole = userMapper.selectRoleByAccount(account);
        Integer affectRows;
        if(accountRole >= user.getRole()){
            //若user的权限为root，则此时account权限也为root，
            //不能设置两个root权限，权限不够将affectRows设为0返回false
            if(user.getRole().equals(UserRole.ROOT.getRole())){
                affectRows = 0;
            }else {
                affectRows = userMapper.updateUser(user);
            }
        }else {
            affectRows = 0;
        }
        return affectRows != 0;
    }

}
