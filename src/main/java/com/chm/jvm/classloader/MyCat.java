package com.chm.jvm.classloader;

/**
 * Created by charming on 2018/3/30.
 */
public class MyCat {
    public MyCat() {
        System.out.println("MyCat is loaded by "+ this.getClass().getClassLoader());
        //删除项目下的MySimple.class文件，执行Test14 main方法会报异常（父加载器加载的类不能访问子加载器加载的类）
//        System.out.println("from MyCat: "+MySample.class);

    }
}
