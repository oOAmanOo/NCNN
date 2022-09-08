package com.tencent.nanodetncnn;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import android.util.DisplayMetrics;

import com.tencent.nanodetncnn.utils.MyUtils;
import com.tencent.nanodetncnn.model.FoodModelPraise;
import com.tencent.nanodetncnn.model.FridgeFoodSumModel;

import java.util.ArrayList;


public class MyApplication extends Application {

    public static String nowUser;
    public static FoodModelPraise foodModelPraise = new FoodModelPraise();
    public static  ArrayList<FridgeFoodSumModel> choseItem_List = new ArrayList<FridgeFoodSumModel>();
    public static int DisplayMetrics_widthPixels;
    public static int DisplayMetrics_heightPixels;


    @Override
    public void onCreate() {
        super.onCreate();

        DisplayMetrics m_DisplayMetrics = MyUtils.getDisplayMetrics(getApplicationContext());
        DisplayMetrics_widthPixels = m_DisplayMetrics.widthPixels;
        DisplayMetrics_heightPixels = m_DisplayMetrics.heightPixels;
        initPhotoError();


    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }


    public static void initPhotoError() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }


    }




}
