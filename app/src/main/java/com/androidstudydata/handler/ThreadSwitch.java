package com.androidstudydata.handler;

import com.androidstudydata.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Alex
 * Date：2019/11/30
 * Note：线程切换实践
 */
public class ThreadSwitch {

    List<Test> list=new ArrayList<>();

    public void switchTest(){

        //开启线程01
        new Thread(new Runnable() {
            @Override
            public void run() {
                list.add(new Test());
                list.get(0).test();
            }
        },"线程01").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //在主线程中拿出子线程创建的Test对象来执行
        list.get(0).test();
    }


    public static class Test{

        public void test(){
            LogUtils.d(Thread.currentThread().getName()+"调用了test方法");
        }
    }
}
