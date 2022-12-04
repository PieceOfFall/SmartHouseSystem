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
    public boolean setLightBedA(Integer lightBedA){
        ElectricAppliance electricAppliance = new ElectricAppliance();
        Integer lightBedAState = checkLightIntegerLegal(lightBedA);
        electricAppliance.setLightBedA(lightBedAState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean setLightBedB(Integer lightBedB) {
        ElectricAppliance electricAppliance = new ElectricAppliance();
        Integer lightBedBState = checkLightIntegerLegal(lightBedB);
        electricAppliance.setLightBedB(lightBedBState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean setLightLivingRoom(Integer lightLivingRoom) {
        ElectricAppliance electricAppliance = new ElectricAppliance();
        Integer lightLivingRoomState = checkLightIntegerLegal(lightLivingRoom);
        electricAppliance.setLightLivingRoom(lightLivingRoomState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean setLightBathroom(Integer lightBathRoom) {
        ElectricAppliance electricAppliance = new ElectricAppliance();
        Integer lightBathRoomState = checkLightIntegerLegal(lightBathRoom);
        electricAppliance.setLightBathroom(lightBathRoomState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean setSwitchA(Integer switchA) {
        ElectricAppliance electricAppliance = new ElectricAppliance();
        Integer switchAState = checkSwitchIntegerLegal(switchA);
        electricAppliance.setSwitchA(switchAState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean setSwitchB(Integer switchB) {
        ElectricAppliance electricAppliance = new ElectricAppliance();
        Integer switchBState = checkSwitchIntegerLegal(switchB);
        electricAppliance.setSwitchB(switchBState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean setSwitchC(Integer switchC) {
        ElectricAppliance electricAppliance = new ElectricAppliance();
        Integer switchCState = checkSwitchIntegerLegal(switchC);
        electricAppliance.setSwitchC(switchCState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean setCurtainA(Integer curtainA) {
        ElectricAppliance electricAppliance = new ElectricAppliance();
        Integer curtainAState = checkSwitchIntegerLegal(curtainA);
        electricAppliance.setCurtainA(curtainAState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean setCurtainB(Integer curtainB) {
        ElectricAppliance electricAppliance = new ElectricAppliance();
        Integer curtainBState = checkSwitchIntegerLegal(curtainB);
        electricAppliance.setCurtainB(curtainBState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean setWarnLight(Integer warnLight) {
        ElectricAppliance electricAppliance = new ElectricAppliance();
        Integer warnLightState = checkSwitchIntegerLegal(warnLight);
        electricAppliance.setWarnLight(warnLightState);
        Integer affectRows = electricMapper.updateElectricAppliance(electricAppliance);
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    private Integer checkLightIntegerLegal(Integer light){
        if(light > LightState.FULL.getState()){
            light = LightState.FULL.getState();
        }else if (light < LightState.CLOSED.getState()){
            light = LightState.CLOSED.getState();
        }
        return light;
    }

    private Integer checkSwitchIntegerLegal(Integer switchState){
        if(switchState > SwitchState.ON.getState()){
            switchState = SwitchState.ON.getState();
        }else if(switchState < SwitchState.OFF.getState()){
            switchState = SwitchState.OFF.getState();
        }
        return switchState;
    }



}
