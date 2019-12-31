package com.androidstudydata.thread;

/**
 * Author：Alex
 * Date：2019/12/26
 * Note：synchronized的相关测试
 */
public class SynchronizedTest {

    byte[] lock=new byte[0];


    /**
     * synchronized修饰普通方法
     */
    public synchronized void methodSync(){

    }


    /**
     * synchronized修饰代码快女
     */
    public void blockSync(){

        //锁对象是当前类的class对象
        synchronized (SynchronizedTest.class){

        }

        //锁对象是当前的实例对象
        synchronized (this){

        }

        //自己指定了一个锁对象
        synchronized (lock){

        }

    }

    /**
     * synchronized修饰静态方法
     */
    public synchronized static void staticMethodSync(){

    }
}
