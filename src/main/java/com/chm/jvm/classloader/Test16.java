package com.chm.jvm.classloader;

import java.lang.reflect.Method;

/**
 * Created by charming on 2018/4/4.
 */
public class Test16 {

    public static void main(String[] args) throws Exception {
        Test13 loader1 = new Test13("loader1");
        Test13 loader2 = new Test13("loader2");

        Class<?> clazz1 = loader1.loadClass("com.chm.jvm.classloader.MyPerson");
        Class<?> clazz2 = loader2.loadClass("com.chm.jvm.classloader.MyPerson");

        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        method.invoke(object1, object2);

    }
}
