package com.fall.smarthouse.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author FAll
 * @date 2022/12/3 9:57
 */
@Getter
@ToString
@AllArgsConstructor
public enum MenuID {
    // 电控
    POWER_CTRL(1), LIGHT_CTRL(11), SWITCH_CTRL(12), CURTAIN_CTRL(13),
    // 安防
    DEFENCE(2), GAS_DETECT(21), SMOG_DETECT(22), SHAKE_DETECT(23), WARN_LIGHT(24),
    // 场景选择
    MODE_CHANGE(3);

    private int id;
}
