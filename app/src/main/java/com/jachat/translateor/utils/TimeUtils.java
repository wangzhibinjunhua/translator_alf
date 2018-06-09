package com.jachat.translateor.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fan on 2016/6/23.
 */
public class TimeUtils {

    //毫秒转秒
    public static String long2String(long time){

        //毫秒转秒
        int sec = (int) time / 1000 ;
        int min = sec / 60 ;	//分钟
        sec = sec % 60 ;		//秒
        if(min < 10){	//分钟补0
            if(sec < 10){	//秒补0
                return "0"+min+":0"+sec;
            }else{
                return "0"+min+":"+sec;
            }
        }else{
            if(sec < 10){	//秒补0
                return min+":0"+sec;
            }else{
                return min+":"+sec;
            }
        }

    }

    /**
     * 返回当前时间的格式为 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * 返回当前时间的格式为 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurTimeToStr(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(time);
    }

    /**
     * 返回当前时间的格式为 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String secondToStr(long time) {
        long second = 0;
        long minute = 0;
        long hour = 0;
        String secondStr = "";
        String minuteStr = "";
        String hourStr = "";
        hour = time / 3600;
        minute = time / 60;
        second = time % 60;
        //Logger.e("hour =" + hour + "  minute = " + minute + "  second = " + second);
        if(hour < 10 || second > 23){
            hourStr = "0" + hour;
        }else{
            hourStr = "" + hour;
        }

        if(minute < 10 || minute > 59){
            minuteStr = "0" + minute;
        }else{
            minuteStr = "" + minute;
        }

        if(second < 10 || second > 59){
            secondStr = "0" + second;
        }else{
            secondStr = "" + second;
        }

        return hourStr + "." + minuteStr + "." + secondStr;
    }


    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


}
