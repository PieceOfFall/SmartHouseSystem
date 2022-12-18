package com.fall.smarthouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;


/**
 * @author FAll
 * @date 2022/12/2 16:56
 * @description 传感器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    private Long time;
    // 燃气 (百分制 危险值 5)
    private double gas;
    // 烟雾 (单位 ppm 危险值 100)
    private double smog;
    // 温度
    private double temperature;
    // 湿度
    private double humidity;
    // 震动
    private double shake;

}
