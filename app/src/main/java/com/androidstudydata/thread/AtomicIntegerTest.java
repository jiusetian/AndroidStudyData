package com.androidstudydata.thread;

import com.androidstudydata.LogUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author：Alex
 * Date：2019/11/27
 * Note：
 */
public class AtomicIntegerTest {

    //使用AtomicInteger之后线程就安全了
    private static final int THREADS_CONUT = 20;
    public static AtomicInteger count = new AtomicInteger(0);

    public static void increase() {
        //AtomicInteger具有原子性和可见性
        count.incrementAndGet();
    }

    public static void test() {
        Thread[] threads = new Thread[THREADS_CONUT];
        for (int i = 0; i < THREADS_CONUT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
            LogUtils.d("线程活跃数量="+Thread.activeCount());
        }

        while (Thread.activeCount() > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
        }

        System.out.println(count);

    }


    /**
     * 线程不安全的int自加
     */
    public static class IntegerIncreaseTest {

        private static final int THREADS_CONUT = 20;
        public static int count = 0;

        public static void increase() {
            count++;
        }

        public static void test() {
            Thread[] threads = new Thread[THREADS_CONUT];
            for (int i = 0; i < THREADS_CONUT; i++) {
                threads[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1000; i++) {
                            increase();
                        }
                    }
                });
                threads[i].start();
            }

            while (Thread.activeCount() > 1) {
                Thread.yield();
            }
            System.out.println(count);
        }
    }

    /**
     * int用volatile修饰也线程不安全
     */
    public static class IntegerVolatileTest {

        private static final int THREADS_CONUT = 20;
        public static volatile int count = 0;

        public static void increase() {
            count++;
        }

        public static void test() {
            Thread[] threads = new Thread[THREADS_CONUT];
            for (int i = 0; i < THREADS_CONUT; i++) {
                threads[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1000; i++) {
                            increase();
                        }
                    }
                });
                threads[i].start();
            }

            while (Thread.activeCount() > 1) {
                Thread.yield();
            }
            System.out.println(count);
        }
    }
}
