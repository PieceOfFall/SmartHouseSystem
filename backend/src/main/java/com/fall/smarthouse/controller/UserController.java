package com.fall.smarthouse.controller;

import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.service.IUserService;
import com.fall.smarthouse.util.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/1/6 20:03
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    IUserService userService;


    @ApiOperation("获取用户创建时间")
    @GetMapping("get_user_create_time")
    public ResBean getCreatTime(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token = request.getHeader("Authorization");
        String account = JWTUtil.validateToken(token);
        Long creatTime = userService.getCreatTime(account);
        response.setStatus(200);
        return ResBean.ok("ok", creatTime);
    }
}
