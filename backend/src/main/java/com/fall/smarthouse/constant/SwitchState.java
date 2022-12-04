package com.fall.smarthouse.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/4 17:57
 */
@Getter
@ToString
@AllArgsConstructor
public enum SwitchState {
    OFF(0),ON(1);
    private Integer state;
}
