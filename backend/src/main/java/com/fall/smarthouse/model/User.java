package com.fall.smarthouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author FAll
 * @date 2022/12/2 17:39
 * @description 用户
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // 账号
    private String account;
    // 密码
    private String password;
    //创建时间
    private Long creatTime;
    //邮箱
    private String eMail;
    //权限
    private Integer role;

}
