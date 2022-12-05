package com.fall.smarthouse.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/5 17:05
 */
public class DateConverter {
    /**
     * @description: 将13位时间错转换为时间字符串
     * @author xiaoQe
     * @date 2022/12/5 17:25
     * @version 1.0
     */
    public static String LongToDateString(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }

    /**
     * @description: 将dateString字符串转化为Sql.date
     * @author xiaoQe
     * @date 2022/12/5 17:29
     * @version 1.0
     */
    public static java.sql.Date DateStringToSqlDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.sql.Date date = new Date(sdf.parse(dateString).getTime());
        return date;
    }

    public static java.util.Date DateStringToUtilSql(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = sdf.parse(dateString);
        return date;

    }
}
