package com.chm.jvm.classloader;

import java.io.*;

/**
 * Created by charming on 2018/3/19.
 * 自定义类加载器
 */
public class Test11 extends ClassLoader {

    private String classLoaderName;

    private final String fileExtenstion = ".class";

    public Test11(String classLoaderName) {
        super(); //将系统类加载器当做该类的加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public Test11(String classLoaderName, ClassLoader parent) {
        super(parent); //显示指定该类的加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    //由loadClass()方法调用
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //并不会执行
        System.out.println("findClass invoke:"+name);
        System.out.println("class loader name:"+classLoaderName);

        byte[] data = loadClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    //
    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {
            is = new FileInputStream(new File(name+this.fileExtenstion));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 !=(ch = is.read()))
            {
                baos.write(ch);
            }
            data = baos.toByteArray();

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data;

    }

    private static void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("com.chm.jvm.classloader.Test1");
        Object object = clazz.newInstance();
        System.out.println(object);
    }

    public static void main(String[] args) throws Exception {
        Test11 loader = new Test11("loader1");
        test(loader);
    }

}
