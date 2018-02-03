package com.chm.jvm.classloader;

/**
 * Created by charming on 2018/2/3.
 * 常量在编辑阶段会存入到调用这个常量的方法所在的类的常量池，本质上，调用类并没有直接引用到定义常量的类，
 * 因此并不会触发定义常量的类的初始化。注意：这里指的是将常量放到了MyTest2的常量池中，
 * 之后Test2与Number类就没有任何关系了，甚至，我们可以将Number的class文件删除
 *
 * 助记符：
 * ldc 表示将int,float或是String类型的常量值从常量池推送到栈顶
 * bipush 表示将单自己（-128 ~ 128）的常量值推送到栈顶
 * sipush 表示将一个短整型常量值（-32768 ~ 32767）推送至栈顶
 * iconst_1 表示将int类型1推送至栈顶（iconst_0 ~ iconst_5）
 *
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println(Number.i);
    }
}

class Number {
    public static final String str = "hello world";
    public static final int i = 0;
    static {
        System.out.println("Number static block");
    }
}


