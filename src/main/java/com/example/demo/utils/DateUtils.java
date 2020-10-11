package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: m-mall-common
 * @description: 时间工具类
 * @author: Mr.Wang
 * @create: 2019-07-17 19:39
 **/
public class DateUtils {
    /**
     * 获取距离当天指定天数的年月日
     *
     * @return
     */
    public static Date getFormatDate(Date date,int amount) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, amount);
            date = c.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(date);
            date = simpleDateFormat.parse(format);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当天的年月日
     *
     * @return
     */
    public static Date getToday() {
        Date date = new Date();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(date);
            date = simpleDateFormat.parse(format);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
