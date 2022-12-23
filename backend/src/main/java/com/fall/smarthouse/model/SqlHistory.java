package com.fall.smarthouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/23 15:25
 */
@Data
@AllArgsConstructor
public class SqlHistory {
    //时间
    Long time;
    //电器类型
    Integer electricType;
    //操作类型
    Integer operationType;
    //电器Id
    Character electricId;
}
