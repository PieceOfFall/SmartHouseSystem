package com.fall.smarthouse.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author FAll
 * @date 2022/12/2 17:39
 * @description 用户
 */
@Data
public class User {
    // 账号
    @NotEmpty
    private String account;
    // 密码
    @NotEmpty
    private String password;

}
