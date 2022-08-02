//package com.tencent.nanodetncnn;
//
//public class Jni {
//    static {
//        System.loadLibrary("libJavaCallC");
//
//        JavaCallC javaCallC=new JavaCallC();
//        javaCallC.voidMethod();
//        System.out.println("intMethod="+javaCallC.intMethod(2008));
//        System.out.println("boolMethod="+javaCallC.boolMethod(true));
//        System.out.println("intArrayMethod="+javaCallC.intArrayMethod(new int[]{1,2,3}));
//        String [] result=javaCallC.strArrayMethod(new String []{"happy","new","year"});
//        for(String str:result){
//            System.out.print(str);
//        }
//        result=javaCallC.getName();
//        for(String str:result){
//            System.out.print(str);
//    }
//
//    public native void varifyCheck(char result);
//}
