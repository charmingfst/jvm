package com.chm.jvm.classloader;

import com.sun.crypto.provider.AESKeyGenerator;

/**
 * Created by charming on 2018/4/4.
 * 各类加载所加载的目录
 */
public class Test15 {
    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();

        System.out.println(aesKeyGenerator.getClass().getClassLoader());
        System.out.println(Test15.class.getClassLoader());
    }
}
