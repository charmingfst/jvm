package com.chm.jvm.classloader;

/**
 * Created by charming on 2018/3/16.
 */

class CL {
    static {
        System.out.println("class cl");
    }
}

//调用ClassLoader类的loadClass方法加载一个类，并不是对类的主动使用，不会导致类的初始化
public class Test8 {
    public static void main(String[] args) throws Exception{
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = loader.loadClass("com.chm.jvm.classloader.CL");
        System.out.println(clazz);
        System.out.println("-------------");
        clazz = Class.forName("com.chm.jvm.classloader.CL");
        System.out.println(clazz);
    }
}

/*
class com.chm.jvm.classloader.CL
-------------
class cl
class com.chm.jvm.classloader.CL
 */
