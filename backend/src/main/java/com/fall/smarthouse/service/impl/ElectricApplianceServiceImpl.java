package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.constant.ElectricType;
import com.fall.smarthouse.constant.LightState;
import com.fall.smarthouse.constant.SwitchState;
import com.fall.smarthouse.mapper.ElectricMapper;
import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.model.ReturnHistory;
import com.fall.smarthouse.model.SqlHistory;
import com.fall.smarthouse.service.IElectricApplianceService;
import com.fall.smarthouse.util.DateConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author FAll
 * @date 2022/12/2 17:21
 */
@Service
public class ElectricApplianceServiceImpl implements IElectricApplianceService {

    private static HashMap<String, Integer> electricApplianceMap = new HashMap<>();

    @Autowired
    ElectricMapper electricMapper;

    @Override
    public ElectricAppliance getAllElectricAppliance() {
        return electricMapper.getAppliance();
    }

    @Override
    public ElectricAppliance getLight() {
        return electricMapper.getLight();
    }

    @Override
    public ElectricAppliance getSwitch() {
        return electricMapper.getSwitch();
    }

    @Override
    public ElectricAppliance getCurtain() {
        return electricMapper.getCurtain();
    }

    @Override
    public ElectricAppliance getWarnLight() {
        return electricMapper.getWarnLight();
    }

    @Override
    public Boolean setLightBedA(ElectricAppliance electricAppliance) {
        Integer lightBedA = electricAppliance.getLightBedA();
        Integer lightBedAState = checkLightIntegerLegal(lightBedA);
        electricAppliance.setLightBedA(lightBedAState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        return affectRows != 0;
    }

    @Override
    public Boolean setLightBedB(ElectricAppliance electricAppliance) {
        Integer lightBedB = electricAppliance.getLightBedB();
        Integer lightBedBState = checkLightIntegerLegal(lightBedB);
        electricAppliance.setLightBedB(lightBedBState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        return affectRows != 0;
    }

    @Override
    public Boolean setLightLivingRoom(ElectricAppliance electricAppliance) {
        Integer lightLivingRoom = electricAppliance.getLightLivingRoom();
        Integer lightLivingRoomState = checkLightIntegerLegal(lightLivingRoom);
        electricAppliance.setLightLivingRoom(lightLivingRoomState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        return affectRows != 0;
    }

    @Override
    public Boolean setLightBathroom(ElectricAppliance electricAppliance) {
        Integer lightBathRoom = electricAppliance.getLightBathroom();
        Integer lightBathRoomState = checkLightIntegerLegal(lightBathRoom);
        electricAppliance.setLightBathroom(lightBathRoomState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        return affectRows != 0;
    }

    @Override
    public Boolean setAppliance(ElectricAppliance electricAppliance) {
        //记录电器情况的Map为空则记录初始电器情况
        if (electricApplianceMap.isEmpty()) {
            ElectricAppliance appliance = electricMapper.getAppliance();
            electricApplianceMap.put("lightBedA", appliance.getLightBedA());
            electricApplianceMap.put("lightBedB", appliance.getLightBedB());
            electricApplianceMap.put("lightLivingRoom", appliance.getLightLivingRoom());
            electricApplianceMap.put("lightBathroom", appliance.getLightBathroom());
            electricApplianceMap.put("switchA", appliance.getSwitchA());
            electricApplianceMap.put("switchB", appliance.getSwitchB());
            electricApplianceMap.put("switchC", appliance.getSwitchC());
            electricApplianceMap.put("curtainA", appliance.getCurtainA());
            electricApplianceMap.put("curtainB", appliance.getCurtainB());
        }
        // 将对象非法属性合法化
        ElectricAppliance updateElectric = new ElectricAppliance();

        // 灯光
        updateElectric.setLightBedA(checkLightIntegerLegal(electricAppliance.getLightBedA()));
        updateElectric.setLightBedB(checkLightIntegerLegal(electricAppliance.getLightBedB()));
        updateElectric.setLightLivingRoom(checkLightIntegerLegal(electricAppliance.getLightLivingRoom()));
        updateElectric.setLightBathroom(checkLightIntegerLegal(electricAppliance.getLightBathroom()));
        // 窗帘
        updateElectric.setCurtainA(checkSwitchIntegerLegal(electricAppliance.getCurtainA()));
        updateElectric.setCurtainB(checkSwitchIntegerLegal(electricAppliance.getCurtainB()));
        // 开关
        updateElectric.setSwitchA(checkSwitchIntegerLegal(electricAppliance.getSwitchA()));
        updateElectric.setSwitchB(checkSwitchIntegerLegal(electricAppliance.getSwitchB()));
        updateElectric.setSwitchC(checkSwitchIntegerLegal(electricAppliance.getSwitchC()));
        // 警报
        updateElectric.setWarnLight(checkSwitchIntegerLegal(electricAppliance.getWarnLight()));

        Integer integer = electricMapper.updateElectricAppliance(updateElectric);
        return integer > 0;
    }

    @Override
    public Boolean setSwitchA(ElectricAppliance electricAppliance) {
        Integer switchA = electricAppliance.getSwitchA();
        Integer switchAState = checkSwitchIntegerLegal(switchA);
        electricAppliance.setSwitchA(switchAState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        return affectRows != 0;
    }

    @Override
    public Boolean setSwitchB(ElectricAppliance electricAppliance) {
        Integer switchB = electricAppliance.getSwitchB();
        Integer switchBState = checkSwitchIntegerLegal(switchB);
        electricAppliance.setSwitchB(switchBState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        return affectRows != 0;
    }

    @Override
    public Boolean setSwitchC(ElectricAppliance electricAppliance) {
        Integer switchC = electricAppliance.getSwitchC();
        Integer switchCState = checkSwitchIntegerLegal(switchC);
        electricAppliance.setSwitchC(switchCState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        return affectRows != 0;
    }

    @Override
    public Boolean setCurtainA(ElectricAppliance electricAppliance) {
        Integer curtainA = electricAppliance.getCurtainA();
        Integer curtainAState = checkSwitchIntegerLegal(curtainA);
        electricAppliance.setCurtainA(curtainAState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        return affectRows != 0;
    }

    @Override
    public Boolean setCurtainB(ElectricAppliance electricAppliance) {
        Integer curtainB = electricAppliance.getCurtainB();
        Integer curtainBState = checkSwitchIntegerLegal(curtainB);
        electricAppliance.setCurtainB(curtainBState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        return affectRows != 0;
    }

    @Override
    public Boolean setWarnLight(ElectricAppliance electricAppliance) {
        Integer warnLight = electricAppliance.getWarnLight();
        Integer warnLightState = checkSwitchIntegerLegal(warnLight);
        electricAppliance.setWarnLight(warnLightState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        return affectRows != 0;
    }

    @Override
    public Boolean homeMode() {
        ElectricAppliance homeMode = new ElectricAppliance();
        homeMode.setLightBedA(LightState.BIG.getState());
        homeMode.setLightBedB(LightState.BIG.getState());
        homeMode.setLightBathroom(LightState.BIG.getState());
        homeMode.setLightLivingRoom(LightState.BIG.getState());
        homeMode.setCurtainA(SwitchState.ON.getState());
        homeMode.setCurtainB(SwitchState.ON.getState());
        homeMode.setSwitchA(SwitchState.ON.getState());
        homeMode.setSwitchB(SwitchState.ON.getState());
        homeMode.setSwitchC(SwitchState.ON.getState());
        Integer affectRows = electricMapper.updateElectricAppliance(homeMode);
        return affectRows != 0;
    }

    @Override
    public Boolean leaveHomeMode() {
        ElectricAppliance leaveHomeMode = new ElectricAppliance();
        leaveHomeMode.setLightBedA(LightState.CLOSED.getState());
        leaveHomeMode.setLightBedB(LightState.CLOSED.getState());
        leaveHomeMode.setLightBathroom(LightState.CLOSED.getState());
        leaveHomeMode.setLightLivingRoom(LightState.CLOSED.getState());
        leaveHomeMode.setCurtainA(SwitchState.OFF.getState());
        leaveHomeMode.setCurtainB(SwitchState.OFF.getState());
        leaveHomeMode.setSwitchA(SwitchState.OFF.getState());
        leaveHomeMode.setSwitchB(SwitchState.OFF.getState());
        leaveHomeMode.setSwitchC(SwitchState.OFF.getState());
        Integer affectRows = electricMapper.updateElectricAppliance(leaveHomeMode);
        return affectRows != 0;
    }

    @Override
    public Boolean addElectricHistory(String account, ElectricAppliance electricAppliance) {
        ElectricAppliance judgeAppliance = judgeAppliance(electricAppliance);
        Integer affectRows = electricMapper.insertElectricHistory(new Timestamp(Calendar.getInstance().getTimeInMillis()),
                account, judgeAppliance);
        return affectRows != 0;
    }

    @Override
    public PageInfo<ReturnHistory> getHistory(String account, String startTime,Integer pageNum,Integer pageSize) {
        Timestamp startDate = DateConverter.StringToTimeStamp(startTime);
        Timestamp endDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
        PageHelper.startPage(pageNum,pageSize);
        List<SqlHistory> sqlHistories = electricMapper.selectElectricHistory(account, startDate, endDate);
        List<ReturnHistory> returnHistories = new LinkedList<>();
        for (SqlHistory sqlHistory : sqlHistories) {
            ReturnHistory returnHistory = judgeTypeToReturnHistory(sqlHistory);
            System.out.println(returnHistory);
            returnHistories.add(returnHistory);
        }
        System.out.println(returnHistories);
        PageInfo<ReturnHistory> returnHistoryPageInfo = new PageInfo<>(returnHistories);
        return returnHistoryPageInfo;
    }

    /**
     * @description: 判断电器类型操作类型及电器id方法并将sqlHistory转换为ReturnHistory返回
     * @author xiaoQe
     * @date 2022/12/23 17:17
     * @version 1.0
     */
    private ReturnHistory judgeTypeToReturnHistory(SqlHistory sqlHistory){
        ReturnHistory returnHistory = new ReturnHistory();
        returnHistory.setTime(sqlHistory.getTime());
        if(sqlHistory.getElectricType() == ElectricType.LIGHT.getType()){
            returnHistory.setElectricType("light");
            switch (sqlHistory.getOperationType()){
                case 0:
                    returnHistory.setOperationType("close");
                    break;
                case 1:
                    returnHistory.setOperationType("small");
                    break;
                case 2:
                    returnHistory.setOperationType("big");
                    break;
                case 3:
                    returnHistory.setOperationType("full");
                    break;
            }
            switch (sqlHistory.getElectricId()){
                case 'A':
                    returnHistory.setElectricId("lightBedA");
                    break;
                case 'B':
                    returnHistory.setElectricId("lightBedB");
                    break;
                case 'C':
                    returnHistory.setElectricId("lightLivingRoom");
                    break;
                case 'D':
                    returnHistory.setElectricId("lightBathroom");
                    break;
            }
        }else if(sqlHistory.getElectricType() == ElectricType.SWITCH.getType()){
            returnHistory.setElectricType("switch");
            if(sqlHistory.getOperationType() == SwitchState.ON.getState()){
                returnHistory.setOperationType("on");
            }else if(sqlHistory.getOperationType() == SwitchState.OFF.getState()){
                returnHistory.setOperationType("off");
            }
            returnHistory.setElectricId(returnHistory.getElectricType() + sqlHistory.getElectricId());
        }else if(sqlHistory.getElectricType() == ElectricType.CURTAIN.getType()){
            returnHistory.setElectricType("curtain");
            if(sqlHistory.getOperationType() == SwitchState.ON.getState()){
                returnHistory.setOperationType("on");
            }else if(sqlHistory.getOperationType() == SwitchState.OFF.getState()){
                returnHistory.setOperationType("off");
            }
            returnHistory.setElectricId(returnHistory.getElectricType() + sqlHistory.getElectricId());
        }
        return returnHistory;
    }

    /**
     * @description: 判断appliance是否修改
     * @author xiaoQe
     * @date 2022/12/23 16:18
     * @version 1.0
     */
    private ElectricAppliance judgeAppliance(ElectricAppliance electricAppliance) {
        if (electricAppliance.getLightBedA() != null) {
            if (electricAppliance.getLightBedA() == electricApplianceMap.get("lightBedA")) {
                electricAppliance.setLightBedA(null);
            } else {
                electricApplianceMap.put("lightBedA", electricAppliance.getLightBedA());
            }
        }
        if (electricAppliance.getLightBedB() != null) {
            if (electricAppliance.getLightBedB() == electricApplianceMap.get("lightBedB")) {
                electricAppliance.setLightBedB(null);
            } else {
                electricApplianceMap.put("lightBedB", electricAppliance.getLightBedB());
            }
        }
        if (electricAppliance.getLightLivingRoom() != null) {
            if (electricAppliance.getLightLivingRoom() == electricApplianceMap.get("lightLivingRoom")) {
                electricAppliance.setLightLivingRoom(null);
            } else {
                electricApplianceMap.put("lightLivingRoom", electricAppliance.getLightLivingRoom());
            }
        }
        if (electricAppliance.getLightBathroom() != null) {
            if (electricAppliance.getLightBathroom() == electricApplianceMap.get("lightBathroom")) {
                electricAppliance.setLightBathroom(null);
            } else {
                electricApplianceMap.put("lightBathroom", electricAppliance.getLightBathroom());
            }
        }
        if (electricAppliance.getSwitchA() != null) {
            if (electricAppliance.getSwitchA() == electricApplianceMap.get("switchA")) {
                electricAppliance.setSwitchA(null);
            } else {
                electricApplianceMap.put("switchA", electricAppliance.getSwitchA());
            }
        }
        if (electricAppliance.getSwitchB() != null) {
            if (electricAppliance.getSwitchB() == electricApplianceMap.get("switchB")) {
                electricAppliance.setSwitchB(null);
            } else {
                electricApplianceMap.put("switchB", electricAppliance.getSwitchB());
            }
        }
        if (electricAppliance.getSwitchC() != null) {
            if (electricAppliance.getSwitchC() == electricApplianceMap.get("switchC")) {
                electricAppliance.setSwitchC(null);
            } else {
                electricApplianceMap.put("switchC", electricAppliance.getSwitchC());
            }
        }
        if (electricAppliance.getCurtainA() != null) {
            if(electricAppliance.getCurtainA() == electricApplianceMap.get("curtainA")) {
                electricAppliance.setCurtainA(null);
            }else {
                electricApplianceMap.put("curtainA", electricAppliance.getCurtainA());
            }
        }
        if (electricAppliance.getCurtainB() != null) {
            if (electricAppliance.getCurtainB() == electricApplianceMap.get("curtainB")) {
                electricAppliance.setCurtainB(null);
            }else {
                electricApplianceMap.put("curtainB", electricAppliance.getCurtainB());
            }
        }
        return electricAppliance;
    }

    private Integer checkLightIntegerLegal(Integer light) {
        if (light == null) {
            return null;
        } else if (light > LightState.FULL.getState()) {
            light = LightState.FULL.getState();
        } else if (light < LightState.CLOSED.getState()) {
            light = LightState.CLOSED.getState();
        }
        return light;
    }

    private Integer checkSwitchIntegerLegal(Integer switchState) {
        if (switchState == null) {
            return null;
        } else if (switchState > SwitchState.ON.getState()) {
            switchState = SwitchState.ON.getState();
        } else if (switchState < SwitchState.OFF.getState()) {
            switchState = SwitchState.OFF.getState();
        }
        return switchState;
    }


}
