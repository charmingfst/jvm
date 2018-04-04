package com.chm.jvm.classloader;

/**
 * Created by charming on 2018/4/4.
 */
public class MyPerson {
    private MyPerson myPerson;

    public void  setMyPerson(Object object)
    {
        this.myPerson = (MyPerson) object;
    }
}
