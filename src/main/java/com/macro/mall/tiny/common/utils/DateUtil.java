package com.macro.mall.tiny.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 有关时间的工具类
 * Created by Administrator on 2020/7/24.
 */

public class DateUtil {

    /**
     *Description:判断传进来的时间是否是当天时间
     */
    public static boolean isToday(Date inputJudgeDate) {
        boolean flag = false;
        //获取当前系统时间
        long longDate = System.currentTimeMillis();
        Date nowDate = new Date(longDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(nowDate);
        String subDate = format.substring(0, 10);
        //定义每天的24h时间范围
        String beginTime = subDate + " 00:00:00";
        String endTime = subDate + " 23:59:59";
        Date paseBeginTime = null;
        Date paseEndTime = null;
        try {
            paseBeginTime = dateFormat.parse(beginTime);
            paseEndTime = dateFormat.parse(endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(inputJudgeDate.after(paseBeginTime) && inputJudgeDate.before(paseEndTime)) {
            flag = true;
        }
        return flag;
    }

    /**
     * @Description: 当前时间到今天结束的总的seconds
     */
    public static Long getRemainSecondsOneDay(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return seconds;
    }

    public static void millis2Date(){
        Long beginTime = System.currentTimeMillis();
        Date date = new Date(beginTime+60*60*24*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = sdf.format(date);
        System.out.println("format:"+format);
    }

    /**
     * 给定日期加上指定天数后
     * @param date
     * @param day
     * @return
     */
    public static String addDate(Date date,long day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = date.getTime();
        day = day*24*60*60*1000;
        time+=day;
        return format.format(new Date(time));
    }

    public static void main(String[] args) {
//        millis2Date();
        System.out.println("args = [" + addDate(new Date(),1) + "]");
    }

}
