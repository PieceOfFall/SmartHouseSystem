package com.fall.smarthouse.service;

import com.fall.smarthouse.bean.MenuItem;

import java.util.ArrayList;

/**
 * @author FAll
 * @date 2022/12/2 17:19
 * @description 用户实现接口
 */
public interface IUserService {

    /**
     * @description: 发送邮箱方法
     * @author xiaoQe
     * @date 2022/12/24 18:54
     * @version 1.0
     */
    void sendEmail(String subject,String text,String account);

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
     * @param account
     * @param password
     * @return: java.lang.Boolean
     * @date 2022/12/6 13:36
     */
    String userLogin(String account,String password);

    /**
     * @author FAll
     * @description 校验Token是否过期
     * @param token
     * @return: java.lang.Boolean
     * @date 2022/12/6 16:02
     */
    Boolean checkLogin(String token) ;
}
