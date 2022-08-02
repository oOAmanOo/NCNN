package com.tencent.nanodetncnn;

public class JavaCallC {
    static {
        System.loadLibrary("JavaCallC");



    }
    public native void varifyCheck(char result);
}
