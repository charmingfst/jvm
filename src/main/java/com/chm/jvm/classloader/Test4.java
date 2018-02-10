package com.chm.jvm.classloader;

/**
 * Created by charming on 2018/2/10.
 * 对于数组实例来说，器类型是油JVM在运行期动态生成的，表示为[com.chm.jvm.classloader.MyParent4这种形式，
 * 动态生成的类型，其父类型就是Object。
 * 对于数组来说，JavaDoc经常讲构成数组的元素称为Component,实际上就是将数组降低一个维度后的类型。
 *
 * 助记符：
 * anewarray：表示创建一个引用类型的（如类，接口，数组）数组，并将其引用压入栈顶
 * newarray：表示创建一个指定的原始类型（如：int, float, char等）的数组，并将其引用压入栈顶
 *
 */
public class Test4 {
    public static void main(String[] args) {
//        MyParent4 myParent4 = new MyParent4(); // 首次创建实例肯定会初始化类。
        MyParent4[] myParent4s = new MyParent4[1]; //不会导致MyParent4初始化
        System.out.println(myParent4s.getClass()); //[Lcom.chm.jvm.classloader.MyParent4
        System.out.println(myParent4s.getClass().getSuperclass()); //java.lang.Object

        System.out.println("===========");

        int[] ints = new int[1];
        System.out.println(ints.getClass()); // [I
        System.out.println(ints.getClass().getSuperclass()); //java.lang.Object

        boolean[] booleens = new boolean[2];
        System.out.println(booleens.getClass()); // [Z
        System.out.println(booleens.getClass().getSuperclass()); //java.lang.Object

        byte[] bytes = new byte[3];
        System.out.println(bytes.getClass()); // [B
        System.out.println(bytes.getClass().getSuperclass()); //java.lang.Object

    }
}

class MyParent4 {
    static {
        System.out.print("MyParent4 static block");
    }
}
