package com.fall.smarthouse.mapper;

import com.fall.smarthouse.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author FAll
 * @date 2022/12/2 18:16
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * @description: 通过account获取邮箱
     * @author xiaoQe
     * @date 2022/12/24 19:28
     */
    @Select("select e_mail from user where account = #{account}")
    String selectEmail(String account);

    /**
     * @description: 获取所有邮箱
     * @author xiaoQe
     * @date 2022/12/28 19:21
     */
    @Select("select e_mail from user")
    List<String> selectAllEmail();

    /**
     * @description: 通过account获取用户注册时间
     * @author xiaoQe
     * @date 2022/12/24 20:27
     */
    @Select("select CONCAT(UNIX_TIMESTAMP(creat_time),'000') as creat_time from user where account = #{account}")
    Long selectCreatTime(String account);

    /**
     * @description: 查询所有用户信息（不包含密码）
     * @author xiaoQe
     * @date 2023/1/6 20:41
     */
    @Select("select account,CONCAT(UNIX_TIMESTAMP(creat_time),'000') as creat_time,e_mail,role from user")
    List<User> selectAllUser();

    /**
     * @description: 添加用户信息
     * @author xiaoQe
     * @date 2023/1/6 20:43
     */
    Integer insertUser(User user);

    /**
     * @description: 修改用户信息
     * @author xiaoQe
     * @date 2023/1/6 21:05
     */
    Integer updateUser(User user);

    /**
     * @description: 删除用户信息
     * @author xiaoQe
     * @date 2023/1/6 21:14
     */
    @Delete("delete from user where account = #{account}")
    Integer deleteUser(User user);

    /**
     * @description: 根据用户名查询用户权限
     * @author xiaoQe
     * @date 2023/1/6 21:25
     */
    @Select("select role from user where account = #{account}")
    Integer selectRoleByAccount(String account);

    /**
     * @description: TODO
     * @author xiaoQe
     * @date 2023/1/6 22:20
     */
    @Select("select count(*) from user where role = 2")
    Integer selectRoleRoot();

    /**
     * @description: 判断用户是否存在
     * @author xiaoQe
     * @date 2023/1/13 22:42
     * @version 1.0
     */
    @Select("select count(*) from user where account = #{account}")
    Integer isExist(String account);
}
