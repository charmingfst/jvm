package com.chm.jvm.classloader;

import java.io.*;

/**
 * Created by charming on 2018/3/22.
 * 类的卸载，有自定义的类加载器加载的类可以卸载，由Java虚拟机自带的类加载器所加载的类，在虚拟机生命周期中始终不会被卸载
 * -XX:+TraceClassUnloading 打印类卸载信息
 */
public class Test13 extends ClassLoader {
    private String classLoaderName;

    private String path;

    private final String fileExtenstion = ".class";

    public Test13(String classLoaderName) {
        super(); //将系统类加载器当做该类的加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public Test13(String classLoaderName, ClassLoader parent) {
        super(parent); //显示指定该类的加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public Test13(ClassLoader parent)
    {
        super(parent);
    }


    public void setPath(String path) {
        this.path = path;
    }

    //由loadClass()方法调用
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        /*
         * 当加载classpath下面的文件时，不会打印下面输出语句，因为由系统加载器加载
         * 当加载另外指定目录下的class文件时，系统加载器不能加载，由我们自己的加载器加载，会打印出下面的语句
         */
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

            name = name.replace(".", "\\");

            is = new FileInputStream(new File(this.path+name+this.fileExtenstion));
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



    /*
     * 注意：先删除本项目下Test1.class文件，不然系统类加载器会先加载本项目下的文件
     */
    public static void main(String[] args) throws Exception {
        Test13 loader = new Test13("loader1");
//        loader.setPath("D:\\workspace\\jvm\\build\\classes\\java\\main\\");
        loader.setPath("C:\\Users\\ason\\Desktop\\");
        Class<?> clazz = loader.loadClass("com.chm.jvm.classloader.Test1");
        Object object = clazz.newInstance();
        System.out.println(object);

        loader = null;
        clazz = null;
        object = null;

        System.gc();

        Thread.sleep(20000);

    }
}
