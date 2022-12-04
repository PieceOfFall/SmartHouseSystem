package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.constant.LightState;
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

    private boolean setElectricAppliance(ElectricAppliance electricAppliance) {
        Integer integer = electricMapper.updateElectricAppliance(electricAppliance);
        if(integer == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean setLightBedA(Integer lightBedA){
        ElectricAppliance electricAppliance = new ElectricAppliance();
        Integer lightBedAState = checkLightInteger(lightBedA);
        electricAppliance.setLightBedA(lightBedAState);
        boolean setElectricAppliance = setElectricAppliance(electricAppliance);
        return setElectricAppliance;
    }

    @Override
    public boolean setLightBedB(Integer lightBedB) {
        ElectricAppliance electricAppliance = new ElectricAppliance();
        Integer lightBedBState = checkLightInteger(lightBedB);
        electricAppliance.setLightBedB(lightBedBState);
        boolean setElectricAppliance = setElectricAppliance(electricAppliance);
        return setElectricAppliance;
    }

    private Integer checkLightInteger(Integer light){
        if(light > LightState.FULL.getState()){
            light = LightState.FULL.getState();
        }else if (light < LightState.CLOSED.getState()){
            light = LightState.CLOSED.getState();
        }
        return light;
    }




}
