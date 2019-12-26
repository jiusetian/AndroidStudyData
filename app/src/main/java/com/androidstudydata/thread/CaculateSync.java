package com.androidstudydata.thread;

import com.androidstudydata.LogUtils;

/**
 * Created by XR_liu on 2018/11/27.
 * 有关计算的同步测试
 */
public class CaculateSync {

    //共享数据
    public static int i = 0;
    private byte[] lock = new byte[0];
    private byte[] lock1 = new byte[0];

    //修饰非静态方法，锁是当前类的实例对象
    private synchronized void add() {
        i++;
    }

    //修饰静态方法，锁是当前类的class对象
    private synchronized static void addStatic() {
        i++;
    }

    //修饰方法中的代码块
    private void addArea() {

        //锁可以是任意对象
        synchronized (lock) {
            i++;
        }
    }

    //修饰方法中的代码块
    private void addArea1() {
        //锁可以是任意对象
        synchronized (lock1) {
            i++;
        }
    }

    private Runnable CaculateRnn = new Runnable() {

        @Override
        public void run() {
            for (int j = 0; j < 1000000; j++) {
                //调用静态方法
                addArea();
            }
        }
    };

    private Runnable CaculateRnn1 = new Runnable() {

        @Override
        public void run() {
            for (int j = 0; j < 1000000; j++) {
                //调用静态方法
                addArea1();
            }
        }
    };


    public Thread test() {
        try {
            Thread thread1 = new Thread(CaculateRnn);
            Thread thread2 = new Thread(CaculateRnn);

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            LogUtils.d("i的值=" + CaculateSync.i);
            //i = 0;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main() {

        try {
            //创建两个对象
            Thread thread = new CaculateSync().test();
            Thread thread1 = new CaculateSync().test();
            thread.join();
            thread1.join();
            LogUtils.d("i的值=" + CaculateSync.i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
