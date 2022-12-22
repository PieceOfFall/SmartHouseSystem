package com.fall.smarthouse.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/22 20:05
 */
@Getter
@ToString
@AllArgsConstructor
public enum ElectricType {
    LIGHT(1),SWITCH(2),CURTAIN(4);
    private Integer type;
}
