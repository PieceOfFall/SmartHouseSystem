package com.fall.smarthouse.util;

import java.sql.Timestamp;


/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/5 17:05
 */
public class DateConverter {

    /**
     * @description: 时间字符串转化为时间TimeStamp
     * @author xiaoQe
     * @date 2022/12/14 17:03
     */
    public static Timestamp StringToTimeStamp(String time){
        return new Timestamp(Long.parseLong(time));
    }

}
