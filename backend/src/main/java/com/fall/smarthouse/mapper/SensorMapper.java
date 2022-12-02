package com.fall.smarthouse.mapper;

import com.fall.smarthouse.model.Sensor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @author FAll
 * @date 2022/12/2 17:05
 */
@Mapper
@Repository
public interface SensorMapper {

    /**
     * @param time
     * @author FAll
     * @description 接口可用性测试
     * @return: java.lang.Integer
     * @date 2022/12/2 17:16
     */
    @Insert("insert into sensor (time) values (#{time});")
    Integer testAdd(Timestamp time);

    /**
     * @param
     * @author FAll
     * @description xml可用性测试
     * @return: java.lang.Integer
     * @date 2022/12/2 17:26
     */
    Sensor testMapper();


}
