package com.androidstudydata.thread;

import com.androidstudydata.LogUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by XR_liu on 2018/11/21.
 * 多线程执行
 */
public class MutilThreadExecute {

    public static void threadsExecute() {

        //线程01
       Thread thread01= new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LogUtils.d("执行线程01");
                }
            }
        });
       thread01.setPriority(10);
       thread01.start();

        //线程02
        Thread thread02=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LogUtils.d("执行线程02");
                }

            }
        });
        thread02.setPriority(1);
        thread02.start();


        //线程03
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        TimeUnit.SECONDS.sleep(2);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    LogUtils.d("执行线程03");
//                }
//
//            }
//        }).start();
    }
}
