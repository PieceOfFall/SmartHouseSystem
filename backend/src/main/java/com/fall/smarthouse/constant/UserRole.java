package com.fall.smarthouse.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/1/6 19:11
 */
@Getter
@ToString
@AllArgsConstructor
public enum UserRole {
    ROOT(2),MASTER(1),USER(0);
    private Integer role;
}
