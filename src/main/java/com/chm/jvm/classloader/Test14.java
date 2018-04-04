package com.chm.jvm.classloader;

/**
 * Created by charming on 2018/3/30.
 * 关于命名空间的重要说明
 * 1. 子加载器所加载的类能够访问到父加载器所加载的类
 * 2. 父加载器所加载的类无法访问到子加载器所加载的类
 */
public class Test14 {
    public static void main(String[] args) throws Exception {
        Test13 loader = new Test13("loader");

        loader.setPath("C:\\Users\\ason\\Desktop\\");

        Class clazz = loader.loadClass("com.chm.jvm.classloader.MySample");

        // 如果注释掉该行，那么并不会实例化MySample对象，即MySample构造方法不会被调用
        // 因此不会实例化MyCat对象，即没有对MyCat进行主动使用，这里就不会加载MyCat Class
        Object object = clazz.newInstance();

    }
}
