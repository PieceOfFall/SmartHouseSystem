package com.fall.smarthouse.controller;

import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.constant.SwitchState;
import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.service.IElectricApplianceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;

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
        HashMap<String, Integer> map = new HashMap<>();
        map.put("lightBedA",light.getLightBedA());
        map.put("lightBedB",light.getLightBedB());
        map.put("lightLivingRoom",light.getLightLivingRoom());
        map.put("lightBathroom",light.getLightBathroom());
        response.setStatus(200);
        return ResBean.ok("ok",map);
    }

    @ApiOperation("获取所有开关的状态")
    @GetMapping("get_switch")
    public ResBean getSwitch(HttpServletResponse response){
        ElectricAppliance switchAppliance = electricApplianceService.getSwitch();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("switchA",switchAppliance.getSwitchA());
        map.put("switchB",switchAppliance.getSwitchB());
        map.put("switchC",switchAppliance.getSwitchC());
        response.setStatus(200);
        return ResBean.ok("ok",map);
    }

    @ApiOperation("获取所有窗帘的数据")
    @GetMapping("get_curtain")
    public ResBean getCurtain(HttpServletResponse response){
        ElectricAppliance curtain = electricApplianceService.getCurtain();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("curtainA",curtain.getCurtainA());
        map.put("curtainB",curtain.getCurtainB());
        response.setStatus(200);
        return ResBean.ok("ok",map);
    }

    @ApiOperation("获取警鸣灯光的数据")
    @GetMapping("get_warn_light")
    public ResBean getWarnLight(HttpServletResponse response){
        ElectricAppliance warnLight = electricApplianceService.getWarnLight();
        Boolean warnLightState;
        if(warnLight.getWarnLight() == SwitchState.ON.getState()){
            warnLightState = true;
        }else {
            warnLightState = false;
        }
        HashMap<String, Boolean> warnLightMap = new HashMap<>();
        warnLightMap.put("isWarn",warnLightState);
        response.setStatus(200);
        return ResBean.ok("ok",warnLightMap);
    }


    @ApiOperation("主卧灯光控制")
    @PostMapping("set_light_bed_a")
    public ResBean setLightBedA(@Valid @RequestBody ElectricAppliance electricAppliance,
                                HttpServletResponse response){
        Boolean setLightBedA = electricApplianceService.setLightBedA(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }
    @ApiOperation("次卧灯光控制")
    @PostMapping("set_light_bed_b")
    public ResBean setLightBedB(@Valid @RequestBody ElectricAppliance electricAppliance,
                                HttpServletResponse response){
        Boolean setLightBedB = electricApplianceService.setLightBedB(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("客厅灯光控制")
    @PostMapping("set_light_living_room")
    public ResBean setLightLivingRoom(@Valid @RequestBody ElectricAppliance electricAppliance,
                                      HttpServletResponse response){
        Boolean setLightLivingRoom = electricApplianceService.setLightLivingRoom(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("浴室灯光控制")
    @PostMapping("set_light_bathroom")
    public ResBean setLightBathroom(@Valid @RequestBody ElectricAppliance electricAppliance,
                                    HttpServletResponse response){
        Boolean setLightLivingRoom = electricApplianceService.setLightBathroom(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("开关A控制")
    @PostMapping("set_switch_a")
    public ResBean setSwitchA(@Valid @RequestBody ElectricAppliance electricAppliance,
                              HttpServletResponse response){
        Boolean setLightLivingRoom = electricApplianceService.setSwitchA(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }
    @ApiOperation("开关B控制")
    @PostMapping("set_switch_b")
    public ResBean setSwitchB(@Valid @RequestBody ElectricAppliance electricAppliance,
                              HttpServletResponse response){
        Boolean setLightLivingRoom = electricApplianceService.setSwitchB(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("开关C控制")
    @PostMapping("set_switch_c")
    public ResBean setSwitchC(@Valid @RequestBody ElectricAppliance electricAppliance,
                              HttpServletResponse response){
        Boolean setLightLivingRoom = electricApplianceService.setSwitchC(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("窗帘A控制")
    @PostMapping("set_curtain_a")
    public ResBean setCurtainA(@Valid @RequestBody ElectricAppliance electricAppliance,
                               HttpServletResponse response){
        Boolean setLightLivingRoom = electricApplianceService.setCurtainA(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("窗帘B控制")
    @PostMapping("set_curtain_b")
    public ResBean setCurtainB(@Valid @RequestBody ElectricAppliance electricAppliance,
                               HttpServletResponse response){
        Boolean setLightLivingRoom = electricApplianceService.setCurtainB(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("警鸣灯光控制")
    @PostMapping("set_warn_light")
    public ResBean setWarnLight(@Valid @RequestBody ElectricAppliance electricAppliance,
                                HttpServletResponse response){
        Boolean setLightLivingRoom = electricApplianceService.setWarnLight(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }
    
    @ApiOperation("控制所有电器")
    @PostMapping("set_appliance")
    public ResBean setAppliance(@Valid @RequestBody ElectricAppliance electricAppliance,
                                HttpServletResponse response) {
        Boolean aBoolean = electricApplianceService.setAppliance(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("设置回家模式")
    @PostMapping("set_home_mode")
    public ResBean setHomeMode(HttpServletResponse response){
        Boolean aBoolean = electricApplianceService.homeMode();
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("设置离家模式")
    @PostMapping("set_leave_home_mode")
    public ResBean setLeaveHomeMode(HttpServletResponse response){
        Boolean aBoolean = electricApplianceService.leaveHomeMode();
        response.setStatus(200);
        return ResBean.ok("ok");
    }
}

