package com.chm.jvm.classloader;

/**
 * Created by charming on 2018/2/3.
 * 对于静态变量来说，只有直接定义了该变量的类才会被初始化；
 * 当一个类在初始化时，要求其父类全部都已经初始化完毕了
 * --XX:+TraceClassLoadinig,用于追踪类的加载信息并打印出来
 *
 * --XX:+<option> 表示开启option选项
 * --XX:-<option> 表示关闭option选项
 * --XX:<option> = <value> 表示将option选项的值设置为value
 */
public class Test1 {

    public static void main(String[] args) {
        System.out.println(MyChild1.str);
    }
}

class MyParent1 {
    public static String str = "hello world";

    static {
        System.out.println("MyParent static block");
    }
}

class MyChild1 extends MyParent1 {
    public static String str2 = "welcome";

    static {
        System.out.println("MyChild1 static block");
    }

}
