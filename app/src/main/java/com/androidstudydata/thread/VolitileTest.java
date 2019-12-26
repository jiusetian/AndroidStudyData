package com.androidstudydata.thread;

import com.easysocket.utils.LogUtil;

import java.util.concurrent.TimeUnit;

/**
 * Author：Alex
 * Date：2019/12/26
 * Note：
 */
public class VolitileTest {


   static boolean flag = false;


    public static void test() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!flag) {
                    try {
                        LogUtil.d("执行线程1");
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LogUtil.d("执行线程2");
                    flag=true;
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


}
