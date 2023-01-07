package com.fall.smarthouse.service;

import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.model.ReturnHistory;
import com.github.pagehelper.PageInfo;

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
    ElectricAppliance getAllElectricAppliance();

    /**
     * 获取所有灯的状态
     * @return 电器model只包含灯光数据
     */
    ElectricAppliance getLight();

    /**
     * 获取所有开关的状态
     * @return 电器model只包含开关数据
     */
    ElectricAppliance getSwitch();

    /**
     * 获取所有窗帘的状态
     * @return 电器model只包含窗帘的数据
     */
    ElectricAppliance getCurtain();

    /**
     * 获取警鸣灯光的状态
     * @return 电器model只包含警鸣灯光的数据
     */
    ElectricAppliance getWarnLight();

    /**
     * @description: 主卧灯光控制
     * @author xiaoQe
     * @date 2022/12/4 15:25
     */
    void setLightBedA(ElectricAppliance electricAppliance);

    /**
     * @description: 次卧灯光控制
     * @author xiaoQe
     * @date 2022/12/4 16:46
     */
    Boolean setLightBedB(ElectricAppliance electricAppliance);

    /**
     * @description: 客厅灯光控制
     * @author xiaoQe
     * @date 2022/12/4 16:46
     */
    void setLightLivingRoom(ElectricAppliance electricAppliance);

    /**
     * @description: 浴室灯光控制
     * @author xiaoQe
     * @date 2022/12/4 16:46
     */
    void setLightBathroom(ElectricAppliance electricAppliance);

    /**
     * @param electricAppliance 电器
     * @author FAll
     * @description 控制所有电器
     * @date 2022/12/7 13:37
     */
    void setAppliance(ElectricAppliance electricAppliance);

    /**
     * @description: 开关A控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     */
    void setSwitchA(ElectricAppliance electricAppliance);

    /**
     * @description: 开关B控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     */
    void setSwitchB(ElectricAppliance electricAppliance);

    /**
     * @description: 开关C控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     */
    void setSwitchC(ElectricAppliance electricAppliance);

    /**
     * @description: 窗帘A控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     */
    void setCurtainA(ElectricAppliance electricAppliance);

    /**
     * @description: 窗帘B控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     */
    void setCurtainB(ElectricAppliance electricAppliance);

    /**
     * @description: 警鸣灯光控制
     * @author xiaoQe
     * @date 2022/12/4 18:03
     */
    void setWarnLight(ElectricAppliance electricAppliance);

    /**
     * @description: 设置回家模式
     * @author xiaoQe
     * @date 2022/12/13 19:22
     */
    void homeMode(String account);

    /**
     * @description: 设置离家模式
     * @author xiaoQe
     * @date 2022/12/13 19:32
     */
    void leaveHomeMode(String account);

    /**
     * @description: 添加历史数据方法
     * @author xiaoQe
     * @date 2022/12/22 19:23
     */
    void addElectricHistory(String account, ElectricAppliance electricAppliance);


    /**
     * @description: 查询历史数据方法
     * @author xiaoQe
     * @date 2022/12/23 17:12
     */
    PageInfo<ReturnHistory> getHistory(String account, String startTime,Integer pageNum,Integer pageSize);

    /**
     * @description: 防盗警报接口方法
     * @author xiaoQe
     * @date 2022/12/24 18:49
     */
    ElectricAppliance burglarAlarm();
}
