package com.fall.smarthouse.constant;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author FAll
 * @date 2022/12/2 17:52
 */
@ToString
@AllArgsConstructor
public enum LightState {
    CLOSED(0),SMALL(1),BIG(2),FULL(3);

    private int state;
}
