package com.fall.smarthouse.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author FAll
 * @date 2022/12/2 18:16
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * @author FAll
     * @description 用户登录
     * @return: java.lang.String
     * @date 2022/12/6 13:22
     */
    @Select("select account from user where account = #{account} and password = MD5(#{password})")
    String userLogin(String account,String password);

    /**
     * @description: 通过account获取邮箱
     * @author xiaoQe
     * @date 2022/12/24 19:28
     * @version 1.0
     */
    @Select("select e_mail from user where account = #{account}")
    String selectEmail(String account);
}
