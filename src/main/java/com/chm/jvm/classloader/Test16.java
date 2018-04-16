package com.chm.jvm.classloader;

import java.lang.reflect.Method;

/**
 * Created by charming on 2018/4/4.
 * 命名空间
 * 类加载器的双亲委托模型的好处：
 * 1. 可以确保Java核心库的类型安全：所有的Java应用都至少会引用java.lang.Object类，也就是说在运行期，java.lang.object这个类会被加载到Java虚拟机中；
 * 如果这个加载过程是由Java应用自己的类加载器所完成的，那么很可能会在JVM中存在多个版本的java.lang.Object类，而且这些类之间还是不兼容的，相互不可见的
 * （正是命名空间在发挥着作用）。
 * 2. 可以确保Java核心库所提供的类不会被自定义的类所替代。
 * 3. 不同的类加载器可以为相同名称（binary name）的类创建额外的命名空间。相同名称的类可以并存在Java虚拟机中，只需要用不同的类加载器类加载他们即可。
 * 不同类加载器所加载的类之间是不兼容的，这就相当于在Java虚拟机内部创建了一个又一个相互隔离的Java类空间，这类技术在很多框架中都得到了实际应用。
 *
 * 扩展类加载器加载是jar包 java -Djava.ext.dirs=./ com.chm.jvm.classloader.Test16
 */
public class Test16 {

    public static void main(String[] args) throws Exception {
        Test13 loader1 = new Test13("loader1");
        Test13 loader2 = new Test13("loader2");

        loader1.setPath("C:\\Users\\ason\\Desktop\\");
        loader2.setPath("C:\\Users\\ason\\Desktop\\");

        Class<?> clazz1 = loader1.loadClass("com.chm.jvm.classloader.MyPerson");
        Class<?> clazz2 = loader2.loadClass("com.chm.jvm.classloader.MyPerson");

        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        method.invoke(object1, object2);

    }
}
