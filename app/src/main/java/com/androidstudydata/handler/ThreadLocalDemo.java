package com.androidstudydata.handler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.androidstudydata.LogUtils;

/**
 * Created by Administrator on 2018/11/30.
 */

public class ThreadLocalDemo {

    private java.lang.ThreadLocal<String> booleanThreadLocal = new ThreadLocal<>();

    public void test() {

        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });

        booleanThreadLocal.set("主线程");

        LogUtils.d(Thread.currentThread().getName() + "的值=" + booleanThreadLocal.get());

        new Thread("线程01") {
            @Override
            public void run() {
                super.run();
                booleanThreadLocal.set("线程01");
                Log.d("tag", Thread.currentThread().getName() + "的值=" + booleanThreadLocal.get());
            }
        }.start();

        new Thread("线程02") {
            @Override
            public void run() {
                super.run();
                //booleanThreadLocal.set("线程02");
                Log.d("tag", Thread.currentThread().getName() + "的值=" + booleanThreadLocal.get());
            }
        }.start();

    }
}


