package com.fall.smarthouse.model;

import lombok.Data;

/**
 * @author FAll
 * @date 2022/12/2 17:39
 * @description 用户
 */
@Data
public class User {
    // 账号
    private String account;
    // 密码
    private String password;
}
