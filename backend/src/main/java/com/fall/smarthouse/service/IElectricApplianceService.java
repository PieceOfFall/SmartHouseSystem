package com.fall.smarthouse.service;

import com.fall.smarthouse.model.ElectricAppliance;
import org.springframework.stereotype.Service;

/**
 * @author FAll
 * @date 2022/12/2 17:20
 * @description 电器实现接口
 */
@Service
public interface IElectricApplianceService {
    /**
     * 获取所有电器状态
     * @return 电器model包含所有电器信息
     */
    public ElectricAppliance getAllElectricAppliance();

    /**
     * 获取所有灯的状态
     * @return 电器model只包含灯光数据
     */
    public ElectricAppliance getLight();

    /**
     * 获取所有开关的状态
     * @return 电器model只包含开关数据
     */
    public ElectricAppliance getSwitch();

    /**
     * 获取所有窗帘的状态
     * @return 电器model只包含窗帘的数据
     */
    public ElectricAppliance getCurtain();

    /**
     * 获取警鸣灯光的状态
     * @return 电器model只包含警鸣灯光的数据
     */
    public ElectricAppliance getWarnLight();

    /**
     * 修改light_bed_a的灯光状态
     * @param lightBedAState
     */
    public void setLightBedAState(Integer lightBedAState);
}
