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
    PageInfo<Double> getGasSensorData(String minTime, String maxTime, Integer pageNum, Integer pageSize) throws ParseException;

    /**
     * @description: 根据时间查询烟雾传感器数据
     * @author xiaoQe
     * @date 2022/12/6 15:21
     * @version 1.0
     */
    PageInfo<Double> getSmogSensorData(String minTime,String maxTime,Integer pageNum,Integer pageSize) throws ParseException;

    /**
     * @description: 根据时间查询温度传感器数据
     * @author xiaoQe
     * @date 2022/12/6 15:35
     * @version 1.0
     */
    PageInfo<Double> getTemperatureSensorData(String minTime,String maxTime,Integer pageNum,Integer pageSize) throws ParseException;

    /**
     * @description: 根据时间查询湿度传感器数据
     * @author xiaoQe
     * @date 2022/12/6 16:06
     * @version 1.0
     */
    PageInfo<Double> getHumiditySensorData(String minTime,String maxTime,Integer pageNum,Integer pageSize) throws ParseException;

    /**
     * @description: 根据时间查询震动传感器数据
     * @author xiaoQe
     * @date 2022/12/6 16:18
     * @version 1.0
     */
    PageInfo<Double> getShakeSensorData(String minTime,String maxTime,Integer pageNum,Integer pageSize) throws ParseException;

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
     * @description: TODO
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
    List<Double> getAbnormalGasData(String startTime);

    /**
     * @description: 根据开始时间获取烟雾异常数据
     * @author xiaoQe
     * @date 2022/12/16 20:37
     * @version 1.0
     */
    List<Double> getAbnormalSmogData(String startTime);

    /**
     * @description: 根据开始时间获取温度异常数据
     * @author xiaoQe
     * @date 2022/12/16 20:46
     * @version 1.0
     */
    List<Double> getAbnormalTemperatureData(String startTime);

    /**
     * @description: 根据开始时间获取湿度异常数据
     * @author xiaoQe
     * @date 2022/12/16 20:51
     * @version 1.0
     */
    List<Double> getAbnormalHumidityData(String startTime);

    /**
     * @description: 根据开始时间获取震动异常数据
     * @author xiaoQe
     * @date 2022/12/16 20:56
     * @version 1.0
     */
    List<Double> getAbnormalShakeData(String startTime);
}
