package com.fall.smarthouse.service;

import com.fall.smarthouse.model.Abnormal;
import com.fall.smarthouse.model.Sensor;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


/**
 * @author FAll
 * @date 2022/12/2 17:19
 * @description 传感器实现接口
 */
public interface ISensorService {

    /**
     * @description: 向传感器表中添加数据
     * @author xiaoQe
     * @date 2022/12/5 15:19
     * @version 1.0
     */

    boolean insertToSensor(Sensor sensor) throws ParseException;

    /**
     * @description: 根据时间查询燃气传感器数据
     * @author xiaoQe
     * @date 2022/12/5 18:00
     * @version 1.0
     */
    PageInfo<Map> getGasSensorData(String minTime, String maxTime, Integer pageNum, Integer pageSize,Character queryType);

    /**
     * @description: 根据时间查询烟雾传感器数据
     * @author xiaoQe
     * @date 2022/12/6 15:21
     * @version 1.0
     */
    PageInfo<Map> getSmogSensorData(String minTime,String maxTime,Integer pageNum,Integer pageSize,Character queryType);

    /**
     * @description: 根据时间查询温度传感器数据
     * @author xiaoQe
     * @date 2022/12/6 15:35
     * @version 1.0
     */
    PageInfo<Map> getTemperatureSensorData(String minTime,String maxTime,Integer pageNum,Integer pageSize,Character queryType);

    /**
     * @description: 根据时间查询湿度传感器数据
     * @author xiaoQe
     * @date 2022/12/6 16:06
     * @version 1.0
     */
    PageInfo<Map> getHumiditySensorData(String minTime,String maxTime,Integer pageNum,Integer pageSize,Character queryType);

    /**
     * @description: 根据时间查询震动传感器数据
     * @author xiaoQe
     * @date 2022/12/6 16:18
     * @version 1.0
     */
    PageInfo<Map> getShakeSensorData(String minTime,String maxTime,Integer pageNum,Integer pageSize,Character queryType);

    /**
     * @description: 传感器根据时间进行安全检测 map为空安全，不为空不安全，map返回时间及异常的传感器数据
     * @author xiaoQe
     * @date 2022/12/7 14:47
     * @version 1.0
     */
    Map<String,Object> safetyInspection(String time);

    /**
     * @description: 根据时间查询传感器数据的方法
     * @author xiaoQe
     * @date 2022/12/14 22:35
     * @version 1.0
     */
    PageInfo<Sensor> selectSensorDataByTime(String minTime,String maxTime,Integer pageNum,Integer pageSize);

    /**
     * @description: 插入异常
     * @author xiaoQe
     * @date 2022/12/14 20:18
     * @version 1.0
     */
    Boolean insertAbnormal(Abnormal abnormal);

    /**
     * @description: 修改数据的方法
     * @author xiaoQe
     * @date 2022/12/14 20:23
     * @version 1.0
     */
    Boolean updateAbnormal(Abnormal abnormal);

    /**
     * @description: 客户端断开连接检查的方法
     * @author xiaoQe
     * @date 2022/12/14 22:15
     * @version 1.0
     */
    List<Abnormal> clientDisconnectSelectAbnormalData(String closeTime,String startTime);

    /**
     * @description: 根据异常类型查询所有异常的开始时间
     * @author xiaoQe
     * @date 2022/12/16 19:25
     * @version 1.0
     */
    List<Long> getStartTimeByRiskIndex(Integer riskIndex);

    /**
     * @description: 根据开始时间戳获取燃气异常数据
     * @author xiaoQe
     * @date 2022/12/16 20:27
     * @version 1.0
     */
    PageInfo<Map> getAbnormalGasData(String startTime,Integer pageNum,Integer pageSize,Character queryType);

    /**
     * @description: 根据开始时间获取烟雾异常数据
     * @author xiaoQe
     * @date 2022/12/16 20:37
     * @version 1.0
     */
    PageInfo<Map> getAbnormalSmogData(String startTime,Integer pageNum,Integer pageSize,Character queryType);

    /**
     * @description: 根据开始时间获取温度异常数据
     * @author xiaoQe
     * @date 2022/12/16 20:46
     * @version 1.0
     */
    PageInfo<Map> getAbnormalTemperatureData(String startTime,Integer pageNum,Integer pageSize,Character queryType);

    /**
     * @description: 根据开始时间获取湿度异常数据
     * @author xiaoQe
     * @date 2022/12/16 20:51
     * @version 1.0
     */
    PageInfo<Map> getAbnormalHumidityData(String startTime,Integer pageNum,Integer pageSize,Character queryType);

    /**
     * @description: 根据开始时间获取震动异常数据
     * @author xiaoQe
     * @date 2022/12/16 20:56
     * @version 1.0
     */
    PageInfo<Map> getAbnormalShakeData(String startTime,Integer pageNum,Integer pageSize,Character queryType);

    /**
     * @author FAll
     * @description 当前是否存在环境数据异常
     * @return: java.lang.Boolean
     * @date 2022/12/20 18:42
     */
    Integer isAbnormalExist();
}
