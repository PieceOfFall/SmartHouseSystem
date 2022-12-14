package com.fall.smarthouse.service.impl;

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

import java.util.List;

/**
 * @author FAll
 * @date 2022/12/2 17:21
 */
@Service
public class UserServiceImpl implements IUserService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSenderImpl mailSender;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(JavaMailSenderImpl mailSender,UserMapper userMapper) {
        this.mailSender = mailSender;
        this.userMapper = userMapper;
    }

    @Override
    public Long getCreatTime(String account) {
        return userMapper.selectCreatTime(account);
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
        return userMapper.selectAllUser();
    }

    @Override
    public Boolean addUser(String account, User user) {
        Integer accountRole = userMapper.selectRoleByAccount(account);
        user.setCreatTime(user.getCreatTime()/1000);
        user.setPassword(JWTUtil.SALT_BEFORE + user.getPassword() + JWTUtil.SALT_AFTER);
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
        if(user.getPassword() != null){
            user.setPassword(JWTUtil.SALT_BEFORE + user.getPassword() + JWTUtil.SALT_AFTER);
        }
        Integer affectRows;
        if(accountRole >= user.getRole()){
            //???user????????????root????????????account????????????root???
            //??????????????????root????????????????????????affectRows??????0??????false
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
