package com.fall.smarthouse.mapper;

import com.fall.smarthouse.model.Sensor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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

    /**
     * @description: 添加传感器数据方法
     * @author xiaoQe
     * @date 2022/12/5 17:36
     * @version 1.0
     */
    Integer insertToSensor(Sensor sensor);

    /**
     * @description: 轮询查询所有传感器数据
     * @author xiaoQe
     * @date 2022/12/7 14:52
     * @version 1.0
     */
    List<Sensor> pollingSelectSensorData(Timestamp time);

    /**
     * @description: 通过时间查询传感器数据
     * @author xiaoQe
     * @date 2022/12/14 22:32
     * @version 1.0
     */
    List<Sensor> selectSensorDataByTime(Timestamp minTime,Timestamp maxTime);

    /**
     * @description: 根据异常开始时间查询异常传感器数据接口（用单个mapper实现）
     * @author xiaoQe
     * @date 2022/12/17 22:25
     * @version 1.0
     */
    List<Map> selectAbnormalSensorData(Sensor sensor, Timestamp startTime,Character queryType);

    /**
     * @description: Map测试
     * @author xiaoQe
     * @date 2022/12/17 18:08
     * @version 1.0
     */
    List<Map<String,Object>> testMap();

    /**
     * @description: 查询传感器数据接口
     * @author xiaoQe
     * @date 2022/12/17 19:55
     * @version 1.0
     */
    List<Map> selectSensorDataByQueryType(Timestamp minTime,Timestamp maxTime,Sensor sensor,Character queryType);
}
