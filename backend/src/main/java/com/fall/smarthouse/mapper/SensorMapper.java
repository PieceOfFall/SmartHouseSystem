package com.fall.smarthouse.mapper;

import com.fall.smarthouse.model.Sensor;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

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
     * @description: 查询燃气传感器数据
     * @author xiaoQe
     * @date 2022/12/5 17:42
     * @version 1.0
     */
    @Select({"select  gas from sensor where `time` between #{minTime} and #{maxTime} order by `time` asc"})
    List<Double> selectGasSensorData(Date minTime,Date maxTime);

    /**
     * @description: 查询烟雾传感器数据
     * @author xiaoQe
     * @date 2022/12/5 19:13
     * @version 1.0
     */
    @Select("select smog from sensor where `time` between #{minTime} and #{maxTime} order by `time` asc")
    List<Double> selectSmogSensorData(Date minTime,Date maxTime);

    /**
     * @description: 查询温度传感器数据
     * @author xiaoQe
     * @date 2022/12/6 15:33
     * @version 1.0
     */
    @Select("select temperature from sensor where `time` between #{minTime} and #{maxTime} order by `time` asc")
    List<Double> selectTemperatureSensorData(Date minTime,Date maxTime);

    /**
     * @description: 查询湿度传感器数据
     * @author xiaoQe
     * @date 2022/12/6 16:04
     * @version 1.0
     */
    @Select("select humidity from sensor where `time` between #{minTime} and #{maxTime} order by `time` asc")
    List<Double> selectHumiditySensorData(Date minTime,Date maxTime);

    /**
     * @description: 查询震动传感器数据
     * @author xiaoQe
     * @date 2022/12/6 16:16
     * @version 1.0
     */
    @Select("select shake from sensor where `time` between #{minTime} and #{maxTime} order by `time` asc")
    List<Double> selectShakeSensorData(Date minTime,Date maxTime);

    /**
     * @description: 查询所有传感器数据
     * @author xiaoQe
     * @date 2022/12/7 14:52
     * @version 1.0
     */
    @Select("select * from sensor where `time` between #{minTime} and #{maxTime} order by `time` asc")
    List<Sensor> selectAllSensorData(Date minTime,Date maxTime);
}
