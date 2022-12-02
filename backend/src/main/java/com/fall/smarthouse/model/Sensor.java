package com.fall.smarthouse.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author FAll
 * @date 2022/12/2 16:56
 * @description 传感器
 */
@Data
public class Sensor {

    // 时间戳
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp time;
    // 燃气
    private double gas;
    // 烟雾
    private double smog;
    // 温度
    private double temperature;
    // 湿度
    private double humidity;
    // 震动
    private double shake;

}
