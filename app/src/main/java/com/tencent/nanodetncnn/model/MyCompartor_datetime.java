package com.tencent.nanodetncnn.model;



import com.tencent.nanodetncnn.utils.MyUtils;

import java.util.Comparator;


public class MyCompartor_datetime implements Comparator {


    public int compare(Object arg0, Object arg1) {
        //  o1比o2大，返回-1；o1比o2小，返回1。Map<String, Object>
        FridgeFoodSumModel user0 = (FridgeFoodSumModel) arg0;
        FridgeFoodSumModel user1 = (FridgeFoodSumModel) arg1;

        long day0  = MyUtils.getTwoDay(user0.expireDate,MyUtils.getNowDateShortString());
        long day1  = MyUtils.getTwoDay(user1.expireDate,MyUtils.getNowDateShortString());

        int flag = 0;
        if (day0 > day1) {

            flag = 1;
        } else if (day0< day1) {
            flag = -1;
        }
        return flag;
    }
}
