package com.fall.smarthouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/14 19:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Abnormal {
    //开始时间
    private Long startTime;
    //结束时间
    private Long endTime;
    //危险系数
    private Integer riskIndex;
}
