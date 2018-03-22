package com.chm.jvm.classloader;



import java.io.*;

/**
 * Created by charming on 2018/3/21.
 * 类加载器双亲委托机制
 */
public class Test12 extends ClassLoader {

    private String classLoaderName;

    private String path;

    private final String fileExtenstion = ".class";

    public Test12(String classLoaderName) {
        super(); //将系统类加载器当做该类的加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public Test12(String classLoaderName, ClassLoader parent) {
        super(parent); //显示指定该类的加载器的父加载器
        this.classLoaderName = classLoaderName;
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
      * 先删除本项目下Test1.class文件，不然系统类加载器会先加载本项目下的文件
      * 下面loader和loader2两个类加载器都会加载一次桌面上的Test1.class文件，每个类加载器都有自己的命名空间
      * 命名空间由该加载器及所有父加载器所加载的类组成。
      *
      * 1. 双亲委托机制（不是继承关系而是包含关系）
      * 2. 命名空间
      */
    public static void main(String[] args) throws Exception {
        Test12 loader = new Test12("loader1");
//        loader.setPath("D:\\workspace\\jvm\\build\\classes\\java\\main\\");
        loader.setPath("C:\\Users\\ason\\Desktop\\");
        Class<?> clazz = loader.loadClass("com.chm.jvm.classloader.Test1");
        Object object = clazz.newInstance();
        System.out.println(object);

        Test12 loader2 = new Test12("loader2", loader);
//        loader.setPath("D:\\workspace\\jvm\\build\\classes\\java\\main\\");
        loader.setPath("C:\\Users\\ason\\Desktop\\");
        Class<?> clazz2 = loader.loadClass("com.chm.jvm.classloader.Test1");
        Object object2 = clazz2.newInstance();
        System.out.println(object2);

        Test12 loader3 = new Test12("loader2");
//        loader.setPath("D:\\workspace\\jvm\\build\\classes\\java\\main\\");
        loader.setPath("C:\\Users\\ason\\Desktop\\");
        Class<?> clazz3 = loader.loadClass("com.chm.jvm.classloader.Test1");
        Object object3 = clazz2.newInstance();
        System.out.println(object2);
    }

}
