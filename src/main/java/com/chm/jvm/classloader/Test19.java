package com.chm.jvm.classloader;

/**
 * Created by charming on 2018/4/25.
 * 线程上下文类加载器
 * Java应用运行时的初始线程的上下文类加载器是系统类加载器。在线程中运行的代码可以通过该类加载器来加载类与资源。
 * Launcher构造方法中 Thread.currentThread().setContextClassLoader(this.loader);
 */
public class Test19 implements Runnable {

    Thread thread;

    public Test19() {
        thread = new Thread(this);
        thread.start();
    }

    public void run() {

        ClassLoader classLoader = this.thread.getContextClassLoader();
        this.thread.setContextClassLoader(classLoader);
        System.out.println(classLoader.getClass());
        System.out.println(classLoader.getParent());

    }

    public static void main(String[] args) {
        new Test19();
    }
}
