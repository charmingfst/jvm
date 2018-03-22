package com.chm.jvm.classloader;

/**
 * Created by charming on 2018/3/19.
 * 数组的Class对象不是又类加载器创建，而是由Java运行时动态创建，数组通过其Class对象的getClassLoader()方法得到的类加载器
 * 与数组中元素的类加载器是一样的。对于原生类型的数组，没有类加载器
 */
public class Test10 {
    public static void main(String[] args) {
        String[] strs = new String[2];
        System.out.println(strs.getClass().getClassLoader());

        Test10[] test10 = new Test10[2];
        System.out.println(test10.getClass().getClassLoader());


        int[] ints = new int[2];
        System.out.println(ints.getClass().getClassLoader());
    }
}
