package com.androidstudydata.thread;

import com.androidstudydata.LogUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by XR_liu on 2018/11/21.
 */
public class SyncCodeBlock {

    byte[] lock=new byte[0];
    public int i;

    public void syncTask() {

        //同步代码库
        synchronized (lock) {
            while (true) {
                try {

                    TimeUnit.SECONDS.sleep(2);
                    LogUtils.d("执行了syncTask="+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public  void startTheads(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncTask();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                syncTask();
            }
        }).start();
    }

    public void syncTask02() {
        //同步代码库
        synchronized (this) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    LogUtils.d("执行了syncTask02");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
