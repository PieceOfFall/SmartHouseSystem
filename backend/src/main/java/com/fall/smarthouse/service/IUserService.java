package com.fall.smarthouse.service;

import com.fall.smarthouse.bean.MenuItem;
import com.fall.smarthouse.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FAll
 * @date 2022/12/2 17:19
 * @description 用户实现接口
 */
public interface IUserService {

    /**
     * @description: 查询注册时间方法
     * @author xiaoQe
     * @date 2022/12/24 20:30
     * @version 1.0
     */
    Long getCreatTime(String account);

    /**
     * @description: 发送邮箱方法
     * @author xiaoQe
     * @date 2022/12/24 18:54
     * @version 1.0
     */
    void sendEmail(String subject,String text,String account);

    /**
     * @description: 获取所有邮箱
     * @author xiaoQe
     * @date 2022/12/28 19:38
     * @version 1.0
     */
    List<String> getAllEmail();

    /**
     * @description: 查询所有用户信息
     * @author xiaoQe
     * @date 2023/1/6 21:27
     * @version 1.0
     */
    List<User> getAllUser();

    /**
     * @description: 添加用户信息
     * @author xiaoQe
     * @date 2023/1/6 21:34
     * @version 1.0
     */
    Boolean addUser(String account,User user);

    /**
     * @description: 删除用户
     * @author xiaoQe
     * @date 2023/1/6 22:04
     * @version 1.0
     */
    Boolean deleteUser(String account,User user);

    /**
     * @description: 修改用户信息
     * @author xiaoQe
     * @date 2023/1/6 22:14
     * @version 1.0
     */
    Boolean updateUser(String account,User user);
}
