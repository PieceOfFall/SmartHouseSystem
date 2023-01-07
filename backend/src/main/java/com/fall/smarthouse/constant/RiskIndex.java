package com.fall.smarthouse.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/14 19:36
 */
@Getter
@ToString
@AllArgsConstructor
public enum RiskIndex {
    HUMIDITY_DANGER(1),SMOG_DANGER(2),TEMPERATURE_DANGER(4),GAS_DANGER(8),SHAKE_DANGER(16);
    private final int index;
}
