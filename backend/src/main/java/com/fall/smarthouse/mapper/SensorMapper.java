package com.fall.smarthouse.mapper;

import com.fall.smarthouse.model.Sensor;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
     * @description: 查询燃气传感器数据
     * @author xiaoQe
     * @date 2022/12/5 17:42
     * @version 1.0
     */
    @Select({"select CONCAT(UNIX_TIMESTAMP(`time`),'000') as time, gas from sensor where `time` between #{minTime} and #{maxTime} order by `time` asc"})
    List<Map> selectGasSensorDataBySecond(Timestamp minTime,Timestamp maxTime);

    /**
     * @description: 查询烟雾传感器数据
     * @author xiaoQe
     * @date 2022/12/5 19:13
     * @version 1.0
     */
    @Select("select CONCAT(UNIX_TIMESTAMP(`time`),'000') as time, smog from sensor where `time` between #{minTime} and #{maxTime} order by `time` asc")
    List<Map> selectSmogSensorDataBySecond(Timestamp minTime,Timestamp maxTime);

    /**
     * @description: 查询温度传感器数据
     * @author xiaoQe
     * @date 2022/12/6 15:33
     * @version 1.0
     */
    @Select("select CONCAT(UNIX_TIMESTAMP(`time`),'000')  as time, temperature from sensor where `time` between #{minTime} and #{maxTime} order by `time` asc")
    List<Map> selectTemperatureSensorDataBySecond(Timestamp minTime,Timestamp maxTime);

    /**
     * @description: 查询湿度传感器数据
     * @author xiaoQe
     * @date 2022/12/6 16:04
     * @version 1.0
     */
    @Select("select CONCAT(UNIX_TIMESTAMP(`time`),'000')  as time, humidity from sensor where `time` between #{minTime} and #{maxTime} order by `time` asc")
    List<Map> selectHumiditySensorDataBySecond(Timestamp minTime,Timestamp maxTime);

    /**
     * @description: 查询震动传感器数据
     * @author xiaoQe
     * @date 2022/12/6 16:16
     * @version 1.0
     */
    @Select("select CONCAT(UNIX_TIMESTAMP(`time`),'000')  as time, shake from sensor where `time` between #{minTime} and #{maxTime} order by `time` asc")
    List<Map> selectShakeSensorDataBySecond(Timestamp minTime,Timestamp maxTime);

    /**
     * @description: 轮询查询所有传感器数据
     * @author xiaoQe
     * @date 2022/12/7 14:52
     * @version 1.0
     */
    List<Sensor> pollingSelectSensorData(Timestamp time);

    /**
     * @description: TODO
     * @author xiaoQe
     * @date 2022/12/14 22:32
     * @version 1.0
     */
    List<Sensor> selectSensorDataByTime(Timestamp minTime,Timestamp maxTime);

    /**
     * @description: 根据异常开始时间查询燃气异常数据
     * @author xiaoQe
     * @date 2022/12/16 19:40
     * @version 1.0
     */
    List<Double> selectAbnormalGasData(Timestamp startTime);

    /**
     * @description: 根据异常开始时间查询烟雾异常数据
     * @author xiaoQe
     * @date 2022/12/16 20:33
     * @version 1.0
     */
    List<Double> selectAbnormalSmogData(Timestamp startTime);

    /**
     * @description: 根据开始时间获取温度异常数据
     * @author xiaoQe
     * @date 2022/12/16 20:43
     * @version 1.0
     */
    List<Double> selectAbnormalTemperatureData(Timestamp startTime);

    /**
     * @description: 根据开始时间获取湿度异常数据
     * @author xiaoQe
     * @date 2022/12/16 20:49
     * @version 1.0
     */
    List<Double> selectAbnormalHumidityData(Timestamp startTime);

    /**
     * @description: 根据开始时间获取震动异常数据
     * @author xiaoQe
     * @date 2022/12/16 20:54
     * @version 1.0
     */
    List<Double> selectAbnormalShakeData(Timestamp startTime);

    /**
     * @description: TODO
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
