package com.androidstudydata.thread;

import com.androidstudydata.LogUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by XR_liu on 2018/11/20.
 * 线程中断测试，一个正在执行的线程，如果调用了interrupt，会抛出错误，然后结束线程
 */
public class InterruputSleepThread {


    public static void main() throws InterruptedException {

        final Thread t1 = new Thread() {
            @Override
            public void run() {
                //while在try中，通过异常中断就可以退出run循环
                try {
                    while (true) { //判断是否被中断
                        //当前线程处于阻塞状态，异常必须捕捉处理，无法往外抛出
                        //此时线程进入while循环，还没进入休眠的阻塞状态，所以此时中断线程的话，中断状态是true的，所以此时可以跳出while循环，进而可以
                        //结束线程
                        if (this.isInterrupted()) {
                            LogUtils.d("线程被中断");
                            break;
                        }
                    }
                    TimeUnit.SECONDS.sleep(2);
                    LogUtils.d("线程正在执行");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        //中断线程
        t1.interrupt();
        LogUtils.d("线程是否被中断" + t1.isInterrupted());

        /**
         * 输出结果:
         Interruted When Sleep
         interrupt:false
         */
    }
}
