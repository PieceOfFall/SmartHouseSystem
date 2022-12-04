package com.fall.smarthouse.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author FAll
 * @date 2022/12/2 17:42
 * @description 家电
 */
@Data
@NoArgsConstructor
public class ElectricAppliance {

    // 主卧灯光
    Integer lightBedA;
    // 次卧灯光
    Integer lightBedB;
    // 客厅灯光
    Integer lightLivingRoom;
    // 浴室灯光
    Integer lightBathroom;
    // 开关A
    Integer switchA;
    // 开关B
    Integer switchB;
    // 开关C
    Integer switchC;
    // 窗帘A
    Integer curtainA;
    // 窗帘B
    Integer curtainB;
    // 警报灯
    Integer warnLight;

}
