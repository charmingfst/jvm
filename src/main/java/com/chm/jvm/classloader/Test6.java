package com.chm.jvm.classloader;

/**
 * Created by charming on 2018/2/10.
 * 准备阶段给类中静态变量赋默认值，初始化阶段给静态变量赋指定值， 初始化顺序有上到下顺序执行。
 *
 */
public class Test6 {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();

        System.out.println("counter1:"+Singleton.counter1); //1
        System.out.println("counter2:"+Singleton.counter2); //0

    }
}

class Singleton {

    public static int counter1;

    private static Singleton singleton = new Singleton();

    private Singleton() {
        counter1++;
        counter2++;
        System.out.println(counter1); // 1
        System.out.println(counter2); // 1
    }

    public static int counter2 = 0;

    public static Singleton getSingleton()
    {
        return singleton;
    }
}
