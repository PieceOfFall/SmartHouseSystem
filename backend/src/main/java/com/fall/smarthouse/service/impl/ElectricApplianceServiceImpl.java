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
import java.util.*;

/**
 * @author FAll
 * @date 2022/12/2 17:21
 */
@Service
public class ElectricApplianceServiceImpl implements IElectricApplianceService {

    private static final HashMap<String, Integer> electricApplianceMap = new HashMap<>();

    private final ElectricMapper electricMapper;

    @Autowired
    public ElectricApplianceServiceImpl(ElectricMapper electricMapper){
        this.electricMapper = electricMapper;
    }

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
    public void setLightBedA(ElectricAppliance electricAppliance) {
        Integer lightBedA = electricAppliance.getLightBedA();
        Integer lightBedAState = checkLightIntegerLegal(lightBedA);
        electricAppliance.setLightBedA(lightBedAState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
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
    public void setLightLivingRoom(ElectricAppliance electricAppliance) {
        Integer lightLivingRoom = electricAppliance.getLightLivingRoom();
        Integer lightLivingRoomState = checkLightIntegerLegal(lightLivingRoom);
        electricAppliance.setLightLivingRoom(lightLivingRoomState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
    }

    @Override
    public void setLightBathroom(ElectricAppliance electricAppliance) {
        Integer lightBathRoom = electricAppliance.getLightBathroom();
        Integer lightBathRoomState = checkLightIntegerLegal(lightBathRoom);
        electricAppliance.setLightBathroom(lightBathRoomState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
    }

    @Override
    public void setAppliance(ElectricAppliance electricAppliance) {
        //记录电器情况的Map为空则记录初始电器情况
        if (electricApplianceMap.isEmpty()) {
            initializeElectricApplianceMap();
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
    }

    @Override
    public void setSwitchA(ElectricAppliance electricAppliance) {
        Integer switchA = electricAppliance.getSwitchA();
        Integer switchAState = checkSwitchIntegerLegal(switchA);
        electricAppliance.setSwitchA(switchAState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
    }

    @Override
    public void setSwitchB(ElectricAppliance electricAppliance) {
        Integer switchB = electricAppliance.getSwitchB();
        Integer switchBState = checkSwitchIntegerLegal(switchB);
        electricAppliance.setSwitchB(switchBState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
    }

    @Override
    public void setSwitchC(ElectricAppliance electricAppliance) {
        Integer switchC = electricAppliance.getSwitchC();
        Integer switchCState = checkSwitchIntegerLegal(switchC);
        electricAppliance.setSwitchC(switchCState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
    }

    @Override
    public void setCurtainA(ElectricAppliance electricAppliance) {
        Integer curtainA = electricAppliance.getCurtainA();
        Integer curtainAState = checkSwitchIntegerLegal(curtainA);
        electricAppliance.setCurtainA(curtainAState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
    }

    @Override
    public void setCurtainB(ElectricAppliance electricAppliance) {
        Integer curtainB = electricAppliance.getCurtainB();
        Integer curtainBState = checkSwitchIntegerLegal(curtainB);
        electricAppliance.setCurtainB(curtainBState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
    }

    @Override
    public void setWarnLight(ElectricAppliance electricAppliance) {
        Integer warnLight = electricAppliance.getWarnLight();
        Integer warnLightState = checkSwitchIntegerLegal(warnLight);
        electricAppliance.setWarnLight(warnLightState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
    }

    @Override
    public void homeMode(String account) {
        //判断electricApplianceMap是否为空初始化electricApplianceMap
        if (electricApplianceMap.isEmpty()) {
            initializeElectricApplianceMap();
        }
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
        //添加历史记录
        addElectricHistory(account,homeMode);
    }

    @Override
    public void leaveHomeMode(String account) {
        //判断electricApplianceMap是否为空初始化electricApplianceMap
        if (electricApplianceMap.isEmpty()) {
            initializeElectricApplianceMap();
        }
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
        //添加历史记录
        addElectricHistory(account,leaveHomeMode);
    }

    @Override
    public void addElectricHistory(String account, ElectricAppliance electricAppliance) {
        ElectricAppliance judgeAppliance = judgeAppliance(electricAppliance);
        Integer affectRows;
        if (judgeAppliance == null) {
            affectRows = 0;
        } else {
            affectRows = electricMapper.insertElectricHistory(new Timestamp(Calendar.getInstance().getTimeInMillis()),
                    account, judgeAppliance);
        }
    }

    @Override
    public PageInfo<ReturnHistory> getHistory(String account, String startTime, Integer pageNum, Integer pageSize) {
        Timestamp startDate = DateConverter.StringToTimeStamp(startTime);
        Timestamp endDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
        PageHelper.startPage(pageNum, pageSize);
        List<SqlHistory> sqlHistories = electricMapper.selectElectricHistory(account, startDate, endDate);
        List<ReturnHistory> returnHistories = new LinkedList<>();
        for (SqlHistory sqlHistory : sqlHistories) {
            ReturnHistory returnHistory = judgeTypeToReturnHistory(sqlHistory);
            returnHistories.add(returnHistory);
        }
        return new PageInfo<>(returnHistories);
    }

    @Override
    public ElectricAppliance burglarAlarm() {
        ElectricAppliance alertAppliance = new ElectricAppliance();
        alertAppliance.setWarnLight(SwitchState.ON.getState());
        electricMapper.updateElectricAppliance(alertAppliance);
        return alertAppliance;
    }

    /**
     * @description: 初始化electricApplianceMap
     * @author xiaoQe
     * @date 2023/1/8 18:59
     * @version 1.0
     */
    private void initializeElectricApplianceMap(){
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

    /**
     * @description: 判断电器类型操作类型及电器id方法并将sqlHistory转换为ReturnHistory返回
     * @author xiaoQe
     * @date 2022/12/23 17:17
     */
    private ReturnHistory judgeTypeToReturnHistory(SqlHistory sqlHistory) {
        ReturnHistory returnHistory = new ReturnHistory();
        returnHistory.setTime(sqlHistory.getTime());
        if (Objects.equals(sqlHistory.getElectricType(), ElectricType.LIGHT.getType())) {
            returnHistory.setElectricType("light");
            switch (sqlHistory.getOperationType()) {
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
            switch (sqlHistory.getElectricId()) {
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
        } else if (sqlHistory.getElectricType().equals(ElectricType.SWITCH.getType())) {
            returnHistory.setElectricType("switch");
            if (sqlHistory.getOperationType().equals(SwitchState.ON.getState())) {
                returnHistory.setOperationType("on");
            } else if (sqlHistory.getOperationType().equals(SwitchState.OFF.getState())) {
                returnHistory.setOperationType("off");
            }
            returnHistory.setElectricId(returnHistory.getElectricType() + sqlHistory.getElectricId());
        } else if (sqlHistory.getElectricType().equals(ElectricType.CURTAIN.getType())) {
            returnHistory.setElectricType("curtain");
            if (sqlHistory.getOperationType().equals(SwitchState.ON.getState())) {
                returnHistory.setOperationType("on");
            } else if (sqlHistory.getOperationType().equals(SwitchState.OFF.getState())) {
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
     */
    private ElectricAppliance judgeAppliance(ElectricAppliance electricAppliance) {
        //判断电器是否改变
        Integer isChange = 0;
        if (electricAppliance.getLightBedA() != null) {
            if (electricAppliance.getLightBedA().equals(electricApplianceMap.get("lightBedA"))) {
                electricAppliance.setLightBedA(null);
            } else {
                electricApplianceMap.put("lightBedA", electricAppliance.getLightBedA());
                isChange++;
            }
        }
        if (electricAppliance.getLightBedB() != null) {
            if (electricAppliance.getLightBedB().equals(electricApplianceMap.get("lightBedB"))) {
                electricAppliance.setLightBedB(null);
            } else {
                electricApplianceMap.put("lightBedB", electricAppliance.getLightBedB());
                isChange++;
            }
        }
        if (electricAppliance.getLightLivingRoom() != null) {
            if (electricAppliance.getLightLivingRoom().equals(electricApplianceMap.get("lightLivingRoom"))) {
                electricAppliance.setLightLivingRoom(null);
            } else {
                electricApplianceMap.put("lightLivingRoom", electricAppliance.getLightLivingRoom());
                isChange++;
            }
        }
        if (electricAppliance.getLightBathroom() != null) {
            if (electricAppliance.getLightBathroom().equals(electricApplianceMap.get("lightBathroom"))) {
                electricAppliance.setLightBathroom(null);
            } else {
                electricApplianceMap.put("lightBathroom", electricAppliance.getLightBathroom());
                isChange++;
            }
        }
        if (electricAppliance.getSwitchA() != null) {
            if (electricAppliance.getSwitchA().equals(electricApplianceMap.get("switchA"))) {
                electricAppliance.setSwitchA(null);
            } else {
                electricApplianceMap.put("switchA", electricAppliance.getSwitchA());
                isChange++;
            }
        }
        if (electricAppliance.getSwitchB() != null) {
            if (electricAppliance.getSwitchB().equals(electricApplianceMap.get("switchB"))) {
                electricAppliance.setSwitchB(null);
            } else {
                electricApplianceMap.put("switchB", electricAppliance.getSwitchB());
                isChange++;
            }
        }
        if (electricAppliance.getSwitchC() != null) {
            if (electricAppliance.getSwitchC().equals(electricApplianceMap.get("switchC"))) {
                electricAppliance.setSwitchC(null);
            } else {
                electricApplianceMap.put("switchC", electricAppliance.getSwitchC());
                isChange++;
            }
        }
        if (electricAppliance.getCurtainA() != null) {
            if (electricAppliance.getCurtainA().equals(electricApplianceMap.get("curtainA"))) {
                electricAppliance.setCurtainA(null);
            } else {
                electricApplianceMap.put("curtainA", electricAppliance.getCurtainA());
                isChange++;
            }
        }
        if (electricAppliance.getCurtainB() != null) {
            if (electricAppliance.getCurtainB().equals(electricApplianceMap.get("curtainB"))) {
                electricAppliance.setCurtainB(null);
            } else {
                electricApplianceMap.put("curtainB", electricAppliance.getCurtainB());
                isChange++;
            }
        }
        if (isChange.equals(0)) {
            electricAppliance = null;
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
