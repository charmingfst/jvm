package com.chm.jvm.classloader;

import java.net.URL;
import java.util.Enumeration;

/**
 * Created by charming on 2018/3/16.
 */
public class Test9 {

    public static void main(String[] args) throws Exception{
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        String res = "com/chm/jvm/classloader/Test8.class";

        Enumeration<URL> urls = loader.getResources(res);
        System.out.println(loader.getResource(res));
        while (urls.hasMoreElements())
        {
            URL url = urls.nextElement();
            System.out.println(url);
        }

        System.out.println("-------------");

        System.out.println(loader);

        while (null != loader)
        {

            loader = loader.getParent();
            System.out.println(loader);
        }


    }
}
