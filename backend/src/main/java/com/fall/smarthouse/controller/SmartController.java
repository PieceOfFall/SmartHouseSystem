package com.fall.smarthouse.controller;

import com.fall.smarthouse.bean.MenuItem;
import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.service.IElectricApplianceService;
import com.fall.smarthouse.service.IUserService;
import com.fall.smarthouse.service.impl.ElectricApplianceServiceImpl;
import com.fall.smarthouse.service.impl.UserServiceImpl;
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

    @ApiOperation("获取所有电器的数据")
    @GetMapping("/get_all_electric_appliance")
    public ResBean getAllElectricAppliance(HttpServletResponse response){
        ElectricAppliance allElectricAppliance = electricApplianceService.getAllElectricAppliance();
        response.setStatus(200);
        return ResBean.ok("ok",allElectricAppliance);
    }

    @ApiOperation("获取所有灯的数据")
    @GetMapping("get_light")
    public ResBean getLight(HttpServletResponse response){
        ElectricAppliance light = electricApplianceService.getLight();
        response.setStatus(200);
        return ResBean.ok("ok",light);
    }

    @ApiOperation("获取所有灯的状态")
    @GetMapping("get_switch")
    public ResBean getSwitch(HttpServletResponse response){
        ElectricAppliance switchAppliance = electricApplianceService.getSwitch();
        response.setStatus(200);
        return ResBean.ok("ok",switchAppliance);
    }

    @ApiOperation("获取所有窗帘的数据")
    @GetMapping("get_curtain")
    public ResBean getCurtain(HttpServletResponse response){
        ElectricAppliance curtain = electricApplianceService.getCurtain();
        response.setStatus(200);
        return ResBean.ok("ok",curtain);
    }

    @ApiOperation("获取警鸣灯光的数据")
    @GetMapping("get_warn_light")
    public ResBean getWarnLight(HttpServletResponse response){
        ElectricAppliance warnLight = electricApplianceService.getWarnLight();
        response.setStatus(200);
        return ResBean.ok("ok",warnLight);
    }


    @ApiOperation("主卧灯光控制")
    @PostMapping("set_light_bed_a")
    public ResBean setLightBedA(@NotEmpty @RequestParam("lightBedA") Integer LightBedA,
                                HttpServletResponse response){
        boolean setLightBedA = electricApplianceService.setLightBedA(LightBedA.intValue());
        response.setStatus(200);
        return ResBean.ok("ok");
    }
    @ApiOperation("次卧灯光控制")
    @PostMapping("set_light_bed_b")
    public ResBean setLightBedB(@NotEmpty @RequestParam("lightBedB") Integer LightBedB,
                                HttpServletResponse response){
        boolean setLightBedB = electricApplianceService.setLightBedB(LightBedB.intValue());
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("客厅灯光控制")
    @PostMapping("set_light_living_room")
    public ResBean setLightLivingRoom(@NotEmpty @RequestParam("lightLivingRoom") Integer LightLivingRoom,
                                HttpServletResponse response){
        boolean setLightLivingRoom = electricApplianceService.setLightLivingRoom(LightLivingRoom.intValue());
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("浴室灯光控制")
    @PostMapping("set_light_bathroom")
    public ResBean setLightBathroom(@NotEmpty @RequestParam("lightBathroom") Integer LightBathroom,
                                      HttpServletResponse response){
        boolean setLightLivingRoom = electricApplianceService.setLightBathroom(LightBathroom.intValue());
        response.setStatus(200);
        return ResBean.ok("ok");
    }
}
