package com.fall.smarthouse.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author FAll
 * @date 2022/12/2 16:56
 * @description 传感器
 */
@Data
@AllArgsConstructor
public class Sensor {

    @NotNull
    // 时间戳
    private Date time;
    @NotNull
    // 燃气
    private double gas;
    @NotNull
    // 烟雾
    private double smog;
    @NotNull
    // 温度
    private double temperature;
    @NotNull
    // 湿度
    private double humidity;
    @NotNull
    // 震动
    private double shake;

}
