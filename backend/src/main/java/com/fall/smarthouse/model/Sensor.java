package com.fall.smarthouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author FAll
 * @date 2022/12/2 16:56
 * @description 传感器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    // 13(10)位时间戳
    private Long time;
    // 燃气 (百分制 危险值 5)
    private Double gas;
    // 烟雾 (单位 ppm 危险值 100)
    private Double smog;
    // 温度
    private Double temperature;
    // 湿度
    private Double humidity;
    // 震动
    private Double shake;

}
