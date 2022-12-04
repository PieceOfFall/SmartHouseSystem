package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.constant.LightState;
import com.fall.smarthouse.mapper.ElectricMapper;
import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.service.IElectricApplianceService;
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
    public void setLightBedAState(Integer lightBedAState) {
        if(lightBedAState < LightState.CLOSED.getState() || lightBedAState > LightState.FULL.getState()){

        }else {
            electricMapper.updateLightBedA(lightBedAState);
        }
    }




}
