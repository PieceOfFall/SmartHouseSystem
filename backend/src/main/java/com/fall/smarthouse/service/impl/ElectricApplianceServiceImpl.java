package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.constant.LightState;
import com.fall.smarthouse.constant.SwitchState;
import com.fall.smarthouse.mapper.ElectricMapper;
import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.service.IElectricApplianceService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author FAll
 * @date 2022/12/2 17:21
 */
@Service
public class ElectricApplianceServiceImpl implements IElectricApplianceService {

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
        if (affectRows == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean setLightBedB(ElectricAppliance electricAppliance) {
        Integer lightBedB = electricAppliance.getLightBedB();
        Integer lightBedBState = checkLightIntegerLegal(lightBedB);
        electricAppliance.setLightBedB(lightBedBState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if (affectRows == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean setLightLivingRoom(ElectricAppliance electricAppliance) {
        Integer lightLivingRoom = electricAppliance.getLightLivingRoom();
        Integer lightLivingRoomState = checkLightIntegerLegal(lightLivingRoom);
        electricAppliance.setLightLivingRoom(lightLivingRoomState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if (affectRows == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean setLightBathroom(ElectricAppliance electricAppliance) {
        Integer lightBathRoom = electricAppliance.getLightBathroom();
        Integer lightBathRoomState = checkLightIntegerLegal(lightBathRoom);
        electricAppliance.setLightBathroom(lightBathRoomState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if (affectRows == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean setAppliance(ElectricAppliance electricAppliance) {
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
        if(integer<=0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean setSwitchA(ElectricAppliance electricAppliance) {
        Integer switchA = electricAppliance.getSwitchA();
        Integer switchAState = checkSwitchIntegerLegal(switchA);
        electricAppliance.setSwitchA(switchAState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if (affectRows == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean setSwitchB(ElectricAppliance electricAppliance) {
        Integer switchB = electricAppliance.getSwitchB();
        Integer switchBState = checkSwitchIntegerLegal(switchB);
        electricAppliance.setSwitchB(switchBState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if (affectRows == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean setSwitchC(ElectricAppliance electricAppliance) {
        Integer switchC = electricAppliance.getSwitchC();
        Integer switchCState = checkSwitchIntegerLegal(switchC);
        electricAppliance.setSwitchC(switchCState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if (affectRows == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean setCurtainA(ElectricAppliance electricAppliance) {
        Integer curtainA = electricAppliance.getCurtainA();
        Integer curtainAState = checkSwitchIntegerLegal(curtainA);
        electricAppliance.setCurtainA(curtainAState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if (affectRows == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean setCurtainB(ElectricAppliance electricAppliance) {
        Integer curtainB = electricAppliance.getCurtainB();
        Integer curtainBState = checkSwitchIntegerLegal(curtainB);
        electricAppliance.setCurtainB(curtainBState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if (affectRows == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean setWarnLight(ElectricAppliance electricAppliance) {
        Integer warnLight = electricAppliance.getWarnLight();
        Integer warnLightState = checkSwitchIntegerLegal(warnLight);
        electricAppliance.setWarnLight(warnLightState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if (affectRows == 0) {
            return false;
        }
        return true;
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
