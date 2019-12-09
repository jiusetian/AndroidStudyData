package com.androidstudydata.handler;

import android.util.Log;

import com.androidstudydata.LogUtils;

/**
 * Created by Administrator on 2018/11/30.
 */

public class ThreadLocalDemo {

    private java.lang.ThreadLocal<String> threadLocal = new ThreadLocal();

    public void test() {

        threadLocal.set("主线程");

        LogUtils.d(Thread.currentThread().getName() + "的值=" + threadLocal.get());

        new Thread("线程01") {
            @Override
            public void run() {
                super.run();
                threadLocal.set("线程01");
                Log.d("tag", Thread.currentThread().getName() + "的值=" + threadLocal.get());
            }
        }.start();

        new Thread("线程02") {
            @Override
            public void run() {
                super.run();
                //threadLocal.set("线程02");
                Log.d("tag", Thread.currentThread().getName() + "的值=" + threadLocal.get());
            }
        }.start();

    }
}


