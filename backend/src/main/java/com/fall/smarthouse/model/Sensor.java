package com.fall.smarthouse.model;

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

    private Long time;
    // 燃气 (百分制 危险值 5)
    private double gas;
    // 烟雾 (单位 ppm 危险值 100)
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
