package com.fall.smarthouse.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/5 17:05
 */
public class DateConverter {
    /**
     * @description: 将13位时间戳转换为时间字符串
     * @author xiaoQe
     * @date 2022/12/5 17:25
     * @version 1.0
     */
    public static String LongToDateString(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }

    /**
     * @description: 时间字符串转化为时间TimeStamp
     * @author xiaoQe
     * @date 2022/12/14 17:03
     * @version 1.0
     */
    public static Timestamp StringToTimeStamp(String time){
        Timestamp timestamp = new Timestamp(Long.parseLong(time));
        return timestamp;
    }

    public static java.sql.Date StringToSqlDate(String time) throws ParseException {
        String dateString = LongToDateString(Long.parseLong(time));
        java.sql.Date date = DateStringToSqlDate(dateString);
        return date;
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

    /**
     * @description: dateString字符串转化为Util.date
     * @author xiaoQe
     * @date 2022/12/5 17:34
     * @version 1.0
     */
    public static java.util.Date DateStringToUtilSql(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = sdf.parse(dateString);
        return date;

    }
}
