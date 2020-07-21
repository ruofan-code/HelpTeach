package com.example.demo.utils;

import java.io.Serializable;
import java.util.UUID;

public class Uuid implements Serializable {
        public static String getUuid() {
            String uuid = null;
             //注意replaceAll前面的是正则表达式
            uuid = UUID.randomUUID().toString().replaceAll("-","");
            return uuid;
        }
    public static long getSecond(String time) {
        long s = 0;
        if (time.length() == 8) { //时分秒格式00:00:00
            int index1 = time.indexOf(":");
            int index2 = time.indexOf(":", index1 + 1);
            s = Integer.parseInt(time.substring(0, index1)) * 3600;//小时
            s += Integer.parseInt(time.substring(index1 + 1, index2)) * 60;//分钟
            s += Integer.parseInt(time.substring(index2 + 1));//秒

        }
        if(time.length()==5) {//分秒格式00:00
            s = Integer.parseInt(time.substring(time.length() - 2)); //秒  后两位肯定是秒
            s += Integer.parseInt(time.substring(0, 2)) * 60;    //分钟
        }
        return s;
    }
}
