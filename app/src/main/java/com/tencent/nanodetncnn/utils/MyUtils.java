package com.tencent.nanodetncnn.utils;

import android.app.Instrumentation;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.KeyEvent;

import com.tencent.nanodetncnn.Consts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {


    public static void onBack() {
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                } catch (Exception e) {



                }
            }
        }.start();
    }


    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    //依照日期找出顏色
    public static int checkColorByType(String alertDate, String expireDate) {
        String todayStr = MyUtils.getNowDateShortString();
        long alertDay = MyUtils.getTwoDay(alertDate, todayStr);
        long expireDay = MyUtils.getTwoDay(expireDate, todayStr);

        if (alertDay >= 0) {
            return Consts.Fridge_Food_color_ok;
        } else if (alertDay < 0 && expireDay >= 0) {
            return Consts.Fridge_Food_color_Ready_to_eat;
        } else if (expireDay < 0) {
            return Consts.Fridge_Food_color_Expired;
        }

        return Consts.Fridge_Food_all;
    }


    public static int str2int(String num) {
        try {
            return Integer.parseInt(num);


        } catch (Exception e) {
            return 0;
        }
    }

    public static long str2Long(String num) {
        try {
            return Long.parseLong(num);


        } catch (Exception e) {
            return 0;
        }
    }

    public static String getNowDateTimeString() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
//        return "2022-08-26";
    }

    public static String getNowDateShortString() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(currentTime);
        return dateString;
//        return "2022-08-26";
    }

    public static long getTwoDay(String sj1, String sj2) {
        sj1= sj1.replace("-","/");
        sj2= sj2.replace("-","/");
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return 0;
        }

//        MyLog.d("getTwoDay:"+day);
        return day;
    }

}
