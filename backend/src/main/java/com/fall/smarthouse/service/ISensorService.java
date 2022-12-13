package com.fall.smarthouse.service;

import com.fall.smarthouse.model.Sensor;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;

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
     * @description: 传感器安全检测 true为安全 false为警告
     * @author xiaoQe
     * @date 2022/12/7 14:47
     * @version 1.0
     */
    Boolean safetyInspection();
}
