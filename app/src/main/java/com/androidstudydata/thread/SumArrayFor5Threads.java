package com.androidstudydata.thread;

import com.androidstudydata.KLogUtil;
import com.androidstudydata.LogUtils;

import java.util.concurrent.TimeUnit;

/**
 * Author：Alex
 * Date：2019/11/27
 * Note：用五个线程去计算ints的所有数字之和
 */
public class SumArrayFor5Threads {
    //数组
    private int[] ints = {2, 3, 4, 5, 5, 6, 6, 7, 7, 7, 8, 8, 9, 9, 9, 9, 90, 0};
    private int i;
    private int sum;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (i < ints.length) {
                synchronized (this) {
                    if (i < ints.length) {
                        KLogUtil.INSTANCE.d("当前线程名字=" + Thread.currentThread().getName());
                        sum += ints[i++];
                        LogUtils.d("当前值=" + sum);
                    }
                }
                //给予时间让别的线程获得锁
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    public void startSum() {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(runnable, "线程" + i);
            thread.start();
        }
    }

}
