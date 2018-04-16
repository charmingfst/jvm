package com.chm.jvm.classloader;

import sun.misc.Launcher;

/**
 * Created by charming on 2018/4/14.
 * 在运行期，一个Java类是由改类的完全限定名（binary name，二进制名）和用于加载改类的定义类加载器（defining loader）所共同决定的。
 * 如果同样名字（即相同的完全限定名）的类是由两个不同的加载器所加载，那么这些类就是不同的，即便.class文件的字节码完全一样，
 * 并且从相同的位置加载亦如此。
 */
public class Test17 {
    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

        /*
            内建于JVM中的启动类和加载器会加载java.lang.classLoader已及其他的Java系统内JVM启动时，一块特殊的机器猫会运行，
            它会加载扩展加载器与系统类加载器，这块特殊的机器码叫做启动类加载器（Bootstrap）。

            启动类加载器并不是Java类，而是其他的加载器则都是Java类,启动类加载器是特定于平台的机器指令，它负责开启整个加载过程。

            所有类加载器（除了启动类加载器）都被实现为Java类。不过，总归有一个组件来加载第一个Java类加载器，
            从而让整个加载过程能够顺利进行下去，加载第一个纯Java类加载器就是启动类加载器的职责。

            启动类加载器还会负责加载供JRE正常运行所需要的基本组件，这包括java.util和java.lang包中的类等等。
         */

        System.out.println(ClassLoader.class.getClassLoader());

        //扩展类加载器和系统类加载器也是由启动类加载器所加载，如果某个类加载加载一个类，那么这个类加载器就会尝试加载这个类中所有依赖的其他组件
        System.out.println(Launcher.class.getClassLoader());

        System.out.println("----------");

        //设置系统类加载器，默认为null,如果设置某个值，就会将该值作为系统类加载器，原本的系统类加载器会成为其父类加载器。
        //java -Djava.system.class.loader=com.chm.jvm.classloader.Test13 com.chm.jvm.classloader.Test17
        System.out.println(System.getProperty("java.system.class.loader")); //com.chm.jvm.classloader.Test13
        System.out.println(Test13.class.getClassLoader()); //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(ClassLoader.getSystemClassLoader()); //com.chm.jvm.classloader.Test13@6d06d69c

    }
}
