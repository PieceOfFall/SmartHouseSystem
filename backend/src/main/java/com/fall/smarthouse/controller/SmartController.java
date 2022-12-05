package com.fall.smarthouse.controller;

import com.fall.smarthouse.bean.MenuItem;
import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.service.IElectricApplianceService;
import com.fall.smarthouse.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

/**
 * @author FAll
 * @date 2022/12/2 17:22
 */
@RequestMapping("/smart_house")
@RestController
public class SmartController {

    @Autowired
    IUserService userService;

    @Autowired
    IElectricApplianceService electricApplianceService;

    @ApiOperation("获取侧边栏")
    @GetMapping("/get_menu")
    public ResBean getMenu(HttpServletResponse response) {
        ArrayList<MenuItem> menu = userService.getMenu();
        response.setStatus(200);
        return ResBean.ok("ok", menu);
    }


}
