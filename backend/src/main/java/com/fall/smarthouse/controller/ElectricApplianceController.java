package com.fall.smarthouse.controller;

import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.service.IElectricApplianceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/5 14:52
 */
@RequestMapping("/electric")
@RestController
public class ElectricApplianceController {
    @Autowired
    IElectricApplianceService electricApplianceService;

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

    @ApiOperation("开关A控制")
    @PostMapping("set_switch_a")
    public ResBean setSwitchA(@NotEmpty @RequestParam("switchA") Integer SwitchA,
                              HttpServletResponse response){
        boolean setLightLivingRoom = electricApplianceService.setSwitchA(SwitchA.intValue());
        response.setStatus(200);
        return ResBean.ok("ok");
    }
    @ApiOperation("开关B控制")
    @PostMapping("set_switch_b")
    public ResBean setSwitchB(@NotEmpty @RequestParam("switchB") Integer SwitchB,
                              HttpServletResponse response){
        boolean setLightLivingRoom = electricApplianceService.setSwitchB(SwitchB.intValue());
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("开关C控制")
    @PostMapping("set_switch_c")
    public ResBean setSwitchC(@NotEmpty @RequestParam("switchC") Integer SwitchC,
                              HttpServletResponse response){
        boolean setLightLivingRoom = electricApplianceService.setSwitchC(SwitchC.intValue());
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("窗帘A控制")
    @PostMapping("set_curtain_a")
    public ResBean setCurtainA(@NotEmpty @RequestParam("curtainA") Integer curtainA,
                               HttpServletResponse response){
        boolean setLightLivingRoom = electricApplianceService.setCurtainA(curtainA.intValue());
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("窗帘B控制")
    @PostMapping("set_curtain_b")
    public ResBean setCurtainB(@NotEmpty @RequestParam("curtainB") Integer curtainB,
                               HttpServletResponse response){
        boolean setLightLivingRoom = electricApplianceService.setCurtainB(curtainB.intValue());
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("警鸣灯光控制")
    @PostMapping("set_warn_light")
    public ResBean setWarnLight(@NotEmpty @RequestParam("warnLight") Integer warnLight,
                                HttpServletResponse response){
        boolean setLightLivingRoom = electricApplianceService.setWarnLight(warnLight.intValue());
        response.setStatus(200);
        return ResBean.ok("ok");
    }
}

