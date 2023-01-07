package com.fall.smarthouse.service;

import com.fall.smarthouse.bean.MenuItem;

import java.util.ArrayList;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/1/6 20:02
 */
public interface ISmartService {


    /**
     * @author FAll
     * @description 网页获取侧边栏路由信息
     * @return: java.util.ArrayList<com.fall.smarthouse.bean.MenuItem>
     * @date 2022/12/3 10:17
     */
    ArrayList<MenuItem> getMenu();

    /**
     * @author FAll
     * @description 用户登录
     * @param account 账户
     * @param password 密码
     * @return: java.lang.Boolean
     * @date 2022/12/6 13:36
     */
    String userLogin(String account,String password);

    /**
     * @author FAll
     * @description 校验Token是否过期
     * @param token 令牌
     * @return: java.lang.Boolean
     * @date 2022/12/6 16:02
     */
    Boolean checkLogin(String token) ;
}
