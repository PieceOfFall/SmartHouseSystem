package com.fall.smarthouse.service;

import com.fall.smarthouse.bean.MenuItem;

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
}
