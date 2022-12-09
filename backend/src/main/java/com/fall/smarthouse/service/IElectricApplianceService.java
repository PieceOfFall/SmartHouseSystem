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
    public Boolean setLightBedA(ElectricAppliance electricAppliance);

    /**
     * @description: 次卧灯光控制
     * @author xiaoQe
     * @date 2022/12/4 16:46
     * @version 1.0
     */
    public Boolean setLightBedB(ElectricAppliance electricAppliance);

    /**
     * @description: 客厅灯光控制
     * @author xiaoQe
     * @date 2022/12/4 16:46
     * @version 1.0
     */
    public Boolean setLightLivingRoom(ElectricAppliance electricAppliance);

    /**
     * @description: 浴室灯光控制
     * @author xiaoQe
     * @date 2022/12/4 16:46
     * @version 1.0
     */
    public Boolean setLightBathroom(ElectricAppliance electricAppliance);

    /**
     * @author FAll
     * @description 控制所有电器
     * @param electricAppliance
     * @return: java.lang.Boolean
     * @date 2022/12/7 13:37
     */
    public Boolean setAppliance(ElectricAppliance electricAppliance);

    /**
     * @description: 开关A控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     * @version 1.0
     */
    public Boolean setSwitchA(ElectricAppliance electricAppliance);

    /**
     * @description: 开关B控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     * @version 1.0
     */
    public Boolean setSwitchB(ElectricAppliance electricAppliance);

    /**
     * @description: 开关C控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     * @version 1.0
     */
    public Boolean setSwitchC(ElectricAppliance electricAppliance);

    /**
     * @description: 窗帘A控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     * @version 1.0
     */
    public Boolean setCurtainA(ElectricAppliance electricAppliance);

    /**
     * @description: 窗帘B控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     * @version 1.0
     */
    public Boolean setCurtainB(ElectricAppliance electricAppliance);

    /**
     * @description: 警鸣灯光控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     * @version 1.0
     */
    public Boolean setWarnLight(ElectricAppliance electricAppliance);
    
}
