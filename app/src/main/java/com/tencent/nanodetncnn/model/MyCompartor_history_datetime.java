package com.tencent.nanodetncnn.model;



import com.tencent.nanodetncnn.utils.MyUtils;

import java.util.Comparator;


public class MyCompartor_history_datetime implements Comparator {


    public int compare(Object arg0, Object arg1) {
        //  o1比o2大，返回-1；o1比o2小，返回1。Map<String, Object>
        FridgeHistoryModel user0 = (FridgeHistoryModel) arg0;
        FridgeHistoryModel user1 = (FridgeHistoryModel) arg1;

        long day0  = MyUtils.getTwoDay(user0.updateDate,MyUtils.getNowDateShortString());
        long day1  = MyUtils.getTwoDay(user1.updateDate,MyUtils.getNowDateShortString());

        int flag = 0;
        if (day0 > day1) {

            flag = -1;
        } else if (day0< day1) {
            flag = 1;
        }
        return flag;
    }
}
