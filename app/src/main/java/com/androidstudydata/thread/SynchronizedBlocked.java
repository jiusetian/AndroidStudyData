package com.androidstudydata.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by XR_liu on 2018/11/20.
 */
public class SynchronizedBlocked implements Runnable{

    //同步方法，锁是当前对象
    public synchronized void f() {
        System.out.println("Trying to call f()");
        while(true) // Never releases lock
            Thread.yield();
    }

    /**
     * 在构造器中创建新线程并启动获取对象锁
     */
    public SynchronizedBlocked() {
        //该线程已持有当前实例锁
        new Thread() {
            public void run() {
                f(); // Lock acquired by this thread
            }
        }.start();
    }

    public void run() {
        //中断判断
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("中断线程!!");
                break;
            } else {
                f(); //请求f方法，首先要得到f方法的锁对象
            }
        }
    }


    public static void main() throws InterruptedException {
        SynchronizedBlocked sync = new SynchronizedBlocked();
        Thread t = new Thread(sync);
        //启动后调用f()方法,无法获取当前实例锁处于等待状态
        t.start();
        TimeUnit.SECONDS.sleep(2);
        //中断线程,无法生效
        t.interrupt();
    }
}
