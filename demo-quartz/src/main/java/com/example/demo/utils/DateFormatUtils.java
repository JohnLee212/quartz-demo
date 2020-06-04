package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

//    private  static ThreadLocal<SimpleDateFormat> formatThreadLocal = new ThreadLocal<>();

    public static String format(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
