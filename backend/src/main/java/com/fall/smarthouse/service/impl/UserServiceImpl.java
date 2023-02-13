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

    // 读取邮箱账户
    @Value("${spring.mail.username}")
    private String fromEmail;

    // mail
    private final JavaMailSenderImpl mailSender;

    // 用户mapper
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
    public Boolean deleteUser(String account, String userAccount) {
        Integer accountRole = userMapper.selectRoleByAccount(account);
        Integer userRole = userMapper.selectRoleByAccount(userAccount);
        Integer affectRows;
        if(accountRole > userRole){
            affectRows = userMapper.deleteUser(userAccount);
        }else {
            affectRows = 0;
        }
        return affectRows != 0;
    }

    @Override
    public Boolean updateUser(String account, User user) {
        // 判断user用户是否存在
        if (userMapper.isExist(user.getAccount()).equals(0)){
            return false;
        }
        Integer accountRole = userMapper.selectRoleByAccount(account);
        // userRole 被修改用户真实权限
        Integer userRole = userMapper.selectRoleByAccount(user.getAccount());
        if(user.getPassword() != null){
            user.setPassword(JWTUtil.SALT_BEFORE + user.getPassword() + JWTUtil.SALT_AFTER);
        }
        Integer affectRows;
        if(accountRole >= userRole){
            // 若user的权限为root，则此时account权限也为root，
            // 不能设置两个root权限，权限不够将affectRows设为0返回false
            if(!account.equals(user.getAccount()) && user.getRole().equals(UserRole.ROOT.getRole())){
                affectRows = 0;
            }else if(accountRole.equals(UserRole.ROOT.getRole()) && account.equals(user.getAccount())) {
                // 进入该判断表明为root用户修改自己的权限
                // 此时将role置空，root用户不可修改自己的权限
                user.setRole(null);
                affectRows = userMapper.updateUser(user);
            }else {
                affectRows = userMapper.updateUser(user);
            }
        }else {
            affectRows = 0;
        }
        return affectRows != 0;
    }

}
