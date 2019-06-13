package com.androidstudydata.handler;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.androidstudydata.LogUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/12/1.
 */

public class HandlerDemo {


    //重写handler的handleMessage方法
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LogUtils.d("handler接收到消息,what内容=" + msg.what);
        }
    };

    //通过传递一个callback实现来处理消息
    Handler handler1 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    public void sendMsg() {
        //在一个子线程中通过主线程的handler发送消息给主线程处理
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //延时两秒
                    TimeUnit.SECONDS.sleep(2);
                    //发送一个消息给handler处理
                    Message msg = new Message();
                    msg.what = 01;
                    handler.sendMessage(msg);

                } catch (InterruptedException e) {

                }
            }
        }).start();


    }

    /**
     * 子线程的handler
     */
    Handler handler3;
    public void createHandler3(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //在子线程中创建handler对象,在子线程中不能在looper创建之前创建handler实例
                Looper.prepare();
                //在子线程中创建一个handler对象，此时这个handler对象中持有的looper实例是保存在当前线程的threadlocalmap中的
                handler3=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        LogUtils.d("handler3收到msg");
                    }
                };

                try {
                    handler3.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LogUtils.d("handler3的post执行");
                        }
                    },2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void post() {
        handler1.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.d("handler1的post方法执行了run方法");
            }
        });
    }

    /**
     * 子线程handler3的post测试,经测试子线程的handler对象不能执行post里面的runnable
     */
    public void handler3Post(){
        createHandler3();
    }


}
