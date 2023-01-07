package com.fall.smarthouse.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/1/6 20:00
 */
@Mapper
@Repository
public interface SmartMapper {

    /**
     * @author FAll
     * @description 用户登录
     * @return: java.lang.String
     * @date 2022/12/6 13:22
     */
    @Select("select account from user where account = #{account} and password = MD5(#{password})")
    String userLogin(String account,String password);
}
