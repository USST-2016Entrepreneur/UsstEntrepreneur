package com.usst.demo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandName {
    public static String getRandName(){
        return getRandName("");
    }
    public static String getRandName(String prefix){
        return getRandName(prefix,"");
    }
    public static String getRandName(String prefix, String postfix){
        return prefix+getCode()+postfix;
    }
    private static synchronized String getCode(){
        return getDateTime() + getRandom(3);
    }
    private static String getRandom(long n) {
        long min = 1,max = 9;
        for (int i = 1; i < n; i++) {
            min *= 10;
            max *= 10;
        }
        Long rangeLong = (((long) (new Random().nextDouble() * (max - min)))) + min ;
        return rangeLong.toString();
    }
    private static String getDateTime(){
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }
}
