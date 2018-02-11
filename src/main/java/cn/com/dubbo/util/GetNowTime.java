package cn.com.dubbo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取当前系统时间工具
 * 
 * @author Lichen.Zheng
 * @email 914001269@qq.com
 * @date 2016-7-25 下午12:24:59
 */
public class GetNowTime {
    public static String getCurrentTime() {  
        String returnStr = null;  
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date date = new Date();  
        returnStr = f.format(date);  
        return returnStr;  
    }  
    /**
     * 获取当前系统时间前一天
     * @return str 时间字符串
     */
    public static String getNextDay() {  
    	Date date = new Date();
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime(); 
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String str = f.format(date);
        return str;  
    }  
    /**
     * 获取当前系统时间前一天
     * @return str
     */
    public static String getWZNextDay() {  
    	Date date = new Date();
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime(); 
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = f.format(date);
        return str;  
    } 
}
