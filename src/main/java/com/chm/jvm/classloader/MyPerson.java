package com.chm.jvm.classloader;

/**
 * Created by charming on 2018/4/4.
 * test16
 */
public class MyPerson {
    private MyPerson myPerson;

    public void  setMyPerson(Object object)
    {
        this.myPerson = (MyPerson) object; //这里不直接传MyPerson对象，而用强制类型转换，是为了让Test16程序能正确运行
    }
}
