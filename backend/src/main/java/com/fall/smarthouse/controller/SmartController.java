package com.fall.smarthouse.controller;

import com.fall.smarthouse.bean.MenuItem;
import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * @author FAll
 * @date 2022/12/2 17:22
 */
@RequestMapping("/smart_house")
@RestController
public class SmartController {

    @Autowired
    UserServiceImpl userService;

    @ApiOperation("获取侧边栏")
    @GetMapping("/get_menu")
    public ResBean getMenu(HttpServletResponse response) {
        ArrayList<MenuItem> menu = userService.getMenu();
        response.setStatus(200);
        return ResBean.ok("ok", menu);
    }
}
