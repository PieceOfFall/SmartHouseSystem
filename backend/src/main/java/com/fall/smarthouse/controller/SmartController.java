package com.fall.smarthouse.controller;

import com.fall.smarthouse.bean.MenuItem;
import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.model.User;
import com.fall.smarthouse.service.ISmartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * @author FAll
 * @date 2022/12/2 17:22
 */
@RequestMapping("/smart_house")
@RestController
public class SmartController {

    // 家居service
    private final ISmartService smartService;

    @Autowired
    public SmartController(ISmartService smartService) {
        this.smartService = smartService;
    }

    @ApiOperation("获取侧边栏")
    @GetMapping("/get_menu")
    public ResBean getMenu(HttpServletResponse response) {
        ArrayList<MenuItem> menu = smartService.getMenu();
        response.setStatus(200);
        return ResBean.ok("ok", menu);
    }

    @ApiOperation("用户登录")
    @PostMapping("/user_login")
    public ResBean userLogin(@Valid @RequestBody User user,
                             HttpServletResponse response) {
        String token = smartService.userLogin(user.getAccount(), user.getPassword());
        if (token == null) {
            response.setStatus(401);
            return ResBean.unauthorized("验证失败");
        }
        response.setStatus(200);
        return ResBean.ok("ok", token);
    }

    @ApiOperation("检测用户登录是否过期 (页面拦截器用)")
    @PostMapping("/check_login")
    public ResBean checkLogin(HttpServletRequest request,
                              HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        Boolean isLogin = smartService.checkLogin(token);

        if (isLogin) {
            response.setStatus(200);
            return ResBean.ok("ok");
        }
        response.setStatus(403);
        return ResBean.forbidden("验证失败，请重新登录");

    }

}
