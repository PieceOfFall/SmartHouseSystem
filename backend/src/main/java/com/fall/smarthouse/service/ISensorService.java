package com.fall.smarthouse.service;

import com.fall.smarthouse.model.Sensor;

import java.sql.Timestamp;
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
    public boolean insertToSensor(String time,Double gas,Double smog,Double temperature,Double humidity,Double shake) throws ParseException;
}
