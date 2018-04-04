package com.chm.jvm.classloader;

/**
 * Created by charming on 2018/3/30.
 */
public class MySample {

    public MySample() {

        System.out.println("MySample is loaded by "+this.getClass().getClassLoader());

        // MySample由什么加载器加载，MyCat就由哪个加载器加载
        new MyCat();

        System.out.println("from MySample: "+MyCat.class);
    }
}
