package com.androidstudydata.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Administrator on 2018/11/29.
 * 线程池相关
 */

public class ThreadPoolDemo {

    ThreadPoolExecutor executor;
    private final BlockingQueue<Runnable> mWorkQueue = new LinkedBlockingDeque<>(100);
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    ExecutorService scheduledThreadPool=Executors.newScheduledThreadPool(10);
    ExecutorService fixThreadPool=Executors.newFixedThreadPool(10);


    public void excute(Runnable runnable) {
        //创建只有一个线程的线程池
        Executors.newSingleThreadExecutor().execute(runnable);

        scheduledThreadPool.execute(runnable);
        scheduledThreadPool.submit(runnable);
        scheduledThreadPool.shutdown();

    }
}
