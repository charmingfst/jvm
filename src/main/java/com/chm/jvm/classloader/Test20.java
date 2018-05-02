package com.chm.jvm.classloader;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by charming on 2018/4/25.
 * 线程上下文加载器的一般使用模式（获取 - 使用 - 还原）
 *
 * ClassLoader classLoader = Thread.currentThread().getContextClassClassLoader()
 * try {
 *  Thread.currentThread().setContextClassLoader(targetTccl)
 *  myMethod()
 * } finally {
 *   Thread.currentThread().setContextClassLoader(classLoader)
 * }
 *
 * myMethod里面则调用了Thread.currentThread().getContextClassLoader(),获取当前线程的上下文类加载器完成某些操作
 * 如果一个类由类加载器A加载，那么这个类的依赖类也是又相同的类加载器加载的（如果该依赖之前没有被加载过）
 * ContextClassLoader的作用就是为了破坏Java的类加载委托机制
 * 当高层提供了统一的接口让底层去实现，同时又要在高层加载（或者实例化）低层的类时，就必须要通过线程上下文类加载器来帮助高层的
 * ClassLoader找到并加载该类
 */
public class Test20 {
    public static void main(String[] args) throws Exception {

        ServiceLoader<Driver> loader = ServiceLoader.loadInstalled(Driver.class);


        Iterator<Driver> iterator = loader.iterator();

        while (iterator.hasNext())
        {
            Driver driver = iterator.next();

            System.out.println("driver:"+driver.getClass()+",loader:"+driver.getClass().getClassLoader());
        }

        System.out.println(ServiceLoader.class.getClassLoader());
        System.out.println("线程上下文件加载器："+Thread.currentThread().getContextClassLoader());


        Class.forName("com.mysql.jdbc.Driver");
        DriverManager.getConnection("url", "root", "passoword");

    }
}
