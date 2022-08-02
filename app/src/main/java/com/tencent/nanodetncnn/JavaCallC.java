package com.tencent.nanodetncnn;

public class JavaCallC {
    static {
        System.loadLibrary("JavaCallC");
//        System.load("/home/bingo/Public/ncnn-Android-Yolov5/app/src/main/java/libJavaCallC.so");


    }
    public native void varifyCheck(char result);
}
