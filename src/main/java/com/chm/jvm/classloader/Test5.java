package com.chm.jvm.classloader;

import java.util.Random;

/**
 * Created by charming on 2018/2/10.
 * 当一个接口在初始化时，并不要求其父类接口都完成了初始化，
 * 只有在真正使用到父类接口的时候（如引用接口中所定义的常量时），才会初始化。
 */
public class Test5 {
    public static void main(String[] args) {

    }
}
//接口中变量默认是public static final
interface MyParent5 {
    public static final int a = new Random().nextInt(3);
}
//不需要初始化父类接口
interface MyChild5 extends MyParent5 {
    public static final int b = 5;
}

interface MyParentOther {
    public static final int a = 5;
}
//要初始化父类接口
interface MyChildOther extends MyParentOther {
    public static final int b = new Random().nextInt(2);
}