package com.fall.smarthouse.controller;

import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.model.User;
import com.fall.smarthouse.service.IUserService;
import com.fall.smarthouse.util.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

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

    @ApiOperation("获取所有用户信息")
    @GetMapping("get_all_user")
    public ResBean getAllUser(HttpServletResponse response){
        List<User> users = userService.getAllUser();
        response.setStatus(200);
        return ResBean.ok("ok",users);
    }

    @ApiOperation("添加用户")
    @PostMapping("add_user")
    public ResBean addUser(@Valid @RequestBody User user,
                           HttpServletResponse response,
                           HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        String account = JWTUtil.validateToken(token);
        Boolean addSuccess = userService.addUser(account, user);
        if(addSuccess){
            response.setStatus(200);
            return ResBean.ok("ok");
        }else {
            response.setStatus(401);
            return ResBean.unauthorized("权限不足或用户名重复");
        }
    }

    @ApiOperation("删除用户")
    @GetMapping("delete_user")
    public ResBean deleteUser(@Valid @RequestBody User user,
                              HttpServletResponse response,
                              HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        String account = JWTUtil.validateToken(token);
        Boolean deleteSuccess = userService.deleteUser(account, user);
        if(deleteSuccess){
            response.setStatus(200);
            return ResBean.ok("ok");
        }else {
            response.setStatus(401);
            return ResBean.unauthorized("权限不足");
        }
    }

    @ApiOperation("修改用户信息")
    @PostMapping("update_user")
    public ResBean updateUser(@Valid @RequestBody User user,
                              HttpServletResponse response,
                              HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        String account = JWTUtil.validateToken(token);
        Boolean updateSuccess = userService.updateUser(account, user);
        if(updateSuccess){
            response.setStatus(200);
            return ResBean.ok("ok");
        }else {
            response.setStatus(401);
            return ResBean.unauthorized("权限不足");
        }
    }
}
