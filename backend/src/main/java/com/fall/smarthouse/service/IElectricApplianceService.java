package com.fall.smarthouse.service;

import com.fall.smarthouse.model.ElectricAppliance;
import org.springframework.stereotype.Service;

/**
 * @author FAll
 * @date 2022/12/2 17:20
 * @description 电器实现接口
 */
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
     * @description: 主卧灯光控制
     * @author xiaoQe
     * @date 2022/12/4 15:25
     * @version 1.0
     */
    public boolean setLightBedA(ElectricAppliance electricAppliance);

    /**
     * @description: 次卧灯光控制
     * @author xiaoQe
     * @date 2022/12/4 16:46
     * @version 1.0
     */
    public boolean setLightBedB(ElectricAppliance electricAppliance);

    /**
     * @description: 客厅灯光控制
     * @author xiaoQe
     * @date 2022/12/4 16:46
     * @version 1.0
     */
    public boolean setLightLivingRoom(ElectricAppliance electricAppliance);

    /**
     * @description: 浴室灯光控制
     * @author xiaoQe
     * @date 2022/12/4 16:46
     * @version 1.0
     */
    public boolean setLightBathroom(ElectricAppliance electricAppliance);

    /**
     * @description: 开关A控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     * @version 1.0
     */
    public boolean setSwitchA(ElectricAppliance electricAppliance);

    /**
     * @description: 开关B控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     * @version 1.0
     */
    public boolean setSwitchB(ElectricAppliance electricAppliance);

    /**
     * @description: 开关C控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     * @version 1.0
     */
    public boolean setSwitchC(ElectricAppliance electricAppliance);

    /**
     * @description: 窗帘A控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     * @version 1.0
     */
    public boolean setCurtainA(ElectricAppliance electricAppliance);

    /**
     * @description: 窗帘B控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     * @version 1.0
     */
    public boolean setCurtainB(ElectricAppliance electricAppliance);

    /**
     * @description: 警鸣灯光控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     * @version 1.0
     */
    public boolean setWarnLight(ElectricAppliance electricAppliance);
}
