package com.androidstudydata.thread;

import com.androidstudydata.LogUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by XR_liu on 2018/11/20.
 * 线程中断测试，一个正在执行的线程，如果调用了interrupt，会抛出错误，然后结束线程
 */
public class InterruputSleepThread3 {

    public static void main() throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                //while在try中，通过异常中断就可以退出run循环
                try {
                    while (true) {
                        //当前线程处于阻塞状态，异常必须捕捉处理，无法往外抛出
                        TimeUnit.SECONDS.sleep(2);
                        LogUtils.d("线程正在执行");
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interruted When Sleep");
                    boolean interrupt = this.isInterrupted();
                    //中断状态被复位
                    System.out.println("interrupt:" + interrupt);
                    //如果调用了这里，那么线程就执行完毕了
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(6);
        //中断处于阻塞状态的线程
        t1.interrupt();

        /**
         * 输出结果:
         Interruted When Sleep
         interrupt:false
         */
    }
}
