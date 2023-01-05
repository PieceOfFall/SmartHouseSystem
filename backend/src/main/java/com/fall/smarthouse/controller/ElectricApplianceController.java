package com.fall.smarthouse.controller;

import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.constant.SwitchState;
import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.model.ReturnHistory;
import com.fall.smarthouse.service.IElectricApplianceService;
import com.fall.smarthouse.service.IUserService;
import com.fall.smarthouse.util.JWTUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;

/**
 * @author xiaoQe
 * @version 1.0
 * @date 2022/12/5 14:52
 */
@RequestMapping("/electric")
@RestController
public class ElectricApplianceController {
    @Autowired
    IUserService userService;

    @Autowired
    IElectricApplianceService electricApplianceService;

    @ApiOperation("获取所有电器的数据")
    @GetMapping("/get_all_electric_appliance")
    public ResBean getAllElectricAppliance(HttpServletResponse response) {
        ElectricAppliance allElectricAppliance = electricApplianceService.getAllElectricAppliance();
        response.setStatus(200);
        return ResBean.ok("ok", allElectricAppliance);
    }

    @ApiOperation("获取所有灯的数据")
    @GetMapping("get_light")
    public ResBean getLight(HttpServletResponse response) {
        ElectricAppliance light = electricApplianceService.getLight();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("lightBedA", light.getLightBedA());
        map.put("lightBedB", light.getLightBedB());
        map.put("lightLivingRoom", light.getLightLivingRoom());
        map.put("lightBathroom", light.getLightBathroom());
        response.setStatus(200);
        return ResBean.ok("ok", map);
    }

    @ApiOperation("获取所有开关的状态")
    @GetMapping("get_switch")
    public ResBean getSwitch(HttpServletResponse response) {
        ElectricAppliance switchAppliance = electricApplianceService.getSwitch();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("switchA", switchAppliance.getSwitchA());
        map.put("switchB", switchAppliance.getSwitchB());
        map.put("switchC", switchAppliance.getSwitchC());
        response.setStatus(200);
        return ResBean.ok("ok", map);
    }

    @ApiOperation("获取所有窗帘的数据")
    @GetMapping("get_curtain")
    public ResBean getCurtain(HttpServletResponse response) {
        ElectricAppliance curtain = electricApplianceService.getCurtain();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("curtainA", curtain.getCurtainA());
        map.put("curtainB", curtain.getCurtainB());
        response.setStatus(200);
        return ResBean.ok("ok", map);
    }

    @ApiOperation("获取警鸣灯光的数据")
    @GetMapping("get_warn_light")
    public ResBean getWarnLight(HttpServletResponse response) {
        ElectricAppliance warnLight = electricApplianceService.getWarnLight();
        boolean warnLightState;

        warnLightState = SwitchState.ON.getState().equals(warnLight.getWarnLight());
        HashMap<String, Boolean> warnLightMap = new HashMap<>();
        warnLightMap.put("isWarn", warnLightState);
        response.setStatus(200);
        return ResBean.ok("ok", warnLightMap);
    }


    @ApiOperation("主卧灯光控制")
    @PostMapping("set_light_bed_a")
    public ResBean setLightBedA(@Valid @RequestBody ElectricAppliance electricAppliance,
                                HttpServletResponse response) {
        electricApplianceService.setLightBedA(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("次卧灯光控制")
    @PostMapping("set_light_bed_b")
    public ResBean setLightBedB(@Valid @RequestBody ElectricAppliance electricAppliance,
                                HttpServletResponse response) {
        electricApplianceService.setLightBedB(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("客厅灯光控制")
    @PostMapping("set_light_living_room")
    public ResBean setLightLivingRoom(@Valid @RequestBody ElectricAppliance electricAppliance,
                                      HttpServletResponse response) {
        electricApplianceService.setLightLivingRoom(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("浴室灯光控制")
    @PostMapping("set_light_bathroom")
    public ResBean setLightBathroom(@Valid @RequestBody ElectricAppliance electricAppliance,
                                    HttpServletResponse response) {
        electricApplianceService.setLightBathroom(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("开关A控制")
    @PostMapping("set_switch_a")
    public ResBean setSwitchA(@Valid @RequestBody ElectricAppliance electricAppliance,
                              HttpServletResponse response) {
        electricApplianceService.setSwitchA(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("开关B控制")
    @PostMapping("set_switch_b")
    public ResBean setSwitchB(@Valid @RequestBody ElectricAppliance electricAppliance,
                              HttpServletResponse response) {
        electricApplianceService.setSwitchB(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("开关C控制")
    @PostMapping("set_switch_c")
    public ResBean setSwitchC(@Valid @RequestBody ElectricAppliance electricAppliance,
                              HttpServletResponse response) {
        electricApplianceService.setSwitchC(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("窗帘A控制")
    @PostMapping("set_curtain_a")
    public ResBean setCurtainA(@Valid @RequestBody ElectricAppliance electricAppliance,
                               HttpServletResponse response) {
        electricApplianceService.setCurtainA(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("窗帘B控制")
    @PostMapping("set_curtain_b")
    public ResBean setCurtainB(@Valid @RequestBody ElectricAppliance electricAppliance,
                               HttpServletResponse response) {
        electricApplianceService.setCurtainB(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("警鸣灯光控制")
    @PostMapping("set_warn_light")
    public ResBean setWarnLight(@Valid @RequestBody ElectricAppliance electricAppliance,
                                HttpServletResponse response) {
        electricApplianceService.setWarnLight(electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("控制所有电器")
    @PostMapping("set_appliance")
    public ResBean setAppliance(@Valid @RequestBody ElectricAppliance electricAppliance,
                                HttpServletResponse response, HttpServletRequest request) throws Exception {
        electricApplianceService.setAppliance(electricAppliance);
        String token = request.getHeader("Authorization");
        String account = JWTUtil.validateToken(token);
        electricApplianceService.addElectricHistory(account, electricAppliance);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("设置回家模式")
    @PostMapping("set_home_mode")
    public ResBean setHomeMode(HttpServletResponse response) {
        electricApplianceService.homeMode();
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("设置离家模式")
    @PostMapping("set_leave_home_mode")
    public ResBean setLeaveHomeMode(HttpServletResponse response) {
        electricApplianceService.leaveHomeMode();
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("查询历史记录")
    @GetMapping("get_history")
    public ResBean getHistory(@NotEmpty @RequestParam("startTime") String startTime,
                              @NotEmpty @RequestParam("pageNum") Integer pageNum,
                              @NotEmpty @RequestParam("pageSize") Integer pageSize,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token = request.getHeader("Authorization");
        String account = JWTUtil.validateToken(token);
        PageInfo<ReturnHistory> history = electricApplianceService.getHistory(account, startTime, pageNum, pageSize);
        response.setStatus(200);
        return ResBean.ok("ok", history);
    }

    @ApiOperation("防盗警报模拟接口（返回warnLight值）")
    @GetMapping("anti_theft_warning_simulation")
    public ResBean antiTheftTest(HttpServletResponse response, HttpServletRequest request) throws Exception {
        ElectricAppliance warnLight = electricApplianceService.getWarnLight();
        ElectricAppliance burglarAlarm = new ElectricAppliance();
        if (warnLight.getWarnLight().equals(SwitchState.OFF.getState())) {
            burglarAlarm = electricApplianceService.burglarAlarm();
            String token = request.getHeader("Authorization");
            String account = JWTUtil.validateToken(token);
            userService.sendEmail("警告", "防盗警告触发，请查看家中具体情况", account);
            response.setStatus(200);
        }else if(warnLight.getWarnLight().equals(SwitchState.ON.getState())){
            warnLight.setWarnLight(SwitchState.OFF.getState());
            electricApplianceService.setWarnLight(warnLight);
            burglarAlarm = warnLight;
        }
        return ResBean.ok("warn", burglarAlarm);
    }

    @ApiOperation("获取用户创建时间")
    @GetMapping("get_user_create_time")
    public ResBean getCreatTime(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String token = request.getHeader("Authorization");
        String account = JWTUtil.validateToken(token);
        Long creatTime = userService.getCreatTime(account);
        response.setStatus(200);
        return ResBean.ok("ok", creatTime);
    }
}

