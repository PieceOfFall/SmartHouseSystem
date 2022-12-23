package com.fall.smarthouse.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/23 15:34
 */
@Data
@NoArgsConstructor
public class ReturnHistory {
    //时间
    private Long time;
    //电器类型
    private String electricType;
    //操作类型
    private String operationType;
    //电器Id
    private String electricId;
}
