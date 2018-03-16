package com.chm.jvm.classloader;

/**
 * Created by charming on 2018/3/16.
 */

class Parent3 {
    static int a = 3;
    static {
        System.out.println("Parent3 static block");
    }
    static void doSomething()
    {
        System.out.println("doSomething");
    }
}

class Child3 extends Parent3{
    static {
        System.out.println("Child3 static block");
    }
}


public class Test7 {
    static {
        System.out.println("Test7 static block");
    }
    public static void main(String[] args) {
        System.out.println(Child3.a); //不会初始化Child3
        System.out.println("--------");
        Child3.doSomething();
    }

}

/*

Test7 static block
Parent3 static block
3
--------
doSomething

*/