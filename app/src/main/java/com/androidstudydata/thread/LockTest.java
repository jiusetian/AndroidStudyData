package com.androidstudydata.thread;

import com.androidstudydata.LogUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author：Alex
 * Date：2019/11/26
 * Note：ReentrantLock的相关测试
 */
public class LockTest {

    private Lock lock = new ReentrantLock();


    /*
     * 使用完毕释放后其他线程才能获取锁
     */
    public void lockTest(Thread thread) {
        lock.lock();//获取锁
        try {
            System.out.println("线程" + thread.getName() + "获取当前锁"); //打印当前锁的名称
            //如果锁已被其他线程占有则返回false，否则返回true
            LogUtil.INSTANCE.d("再次尝试获得锁="+lock.tryLock());
            Thread.sleep(2000);//为看出执行效果，是线程此处休眠2秒
        } catch (Exception e) {
            System.out.println("线程" + thread.getName() + "发生了异常释放锁");
        } finally {
            System.out.println("线程" + thread.getName() + "执行完毕释放锁");
            lock.unlock(); //释放锁
            lock.unlock();
            //LogUtil.INSTANCE.d("2次尝试获得锁="+lock.tryLock());
        }
    }

    public static void lockTest() {
        final LockTest lockTest = new LockTest();
        //声明一个线程 “线程一”
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest.lockTest(Thread.currentThread());
            }
        }, "thread1");
        //声明一个线程 “线程二”
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                lockTest.lockTest(Thread.currentThread());
            }
        }, "thread2");
        // 启动2个线程
        thread2.start();
        thread1.start();

    }

    /*
     * 尝试获取锁 tryLock() 它表示用来尝试获取锁，如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false
     */
    public void tryLockTest(Thread thread) {

        if (lock.tryLock()) { //尝试获取锁
            try {
                System.out.println("线程" + thread.getName() + "获取当前锁"); //打印当前锁的名称
                Thread.sleep(2000);//为看出执行效果，是线程此处休眠2秒
            } catch (Exception e) {
                System.out.println("线程" + thread.getName() + "发生了异常释放锁");
            } finally {
                System.out.println("线程" + thread.getName() + "执行完毕释放锁");
                lock.unlock(); //释放锁
            }
        } else {
            System.out.println("我是线程" + Thread.currentThread().getName() + "当前锁被别人占用，我无法获取");
        }
    }

    public static void tryLockTest() {
        final LockTest lockTest = new LockTest();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest.tryLockTest(Thread.currentThread());
            }
        }, "thread1");
        //声明一个线程 “线程二”
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                lockTest.tryLockTest(Thread.currentThread());
            }
        }, "thread2");
        // 启动2个线程
        thread2.start();
        thread1.start();
    }

    public void tryLockTimeOutTest(Thread thread) throws InterruptedException {

        if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) { //尝试获取锁 获取不到锁，就等3秒，如果3秒后还是获取不到就返回false
            try {
                System.out.println("线程" + thread.getName() + "获取当前锁"); //打印当前锁的名称
                Thread.sleep(4000);//为看出执行效果，是线程此处休眠2秒
            } catch (Exception e) {
                System.out.println("线程" + thread.getName() + "发生了异常释放锁");
            } finally {
                System.out.println("线程" + thread.getName() + "执行完毕释放锁");
                lock.unlock(); //释放锁
            }
        } else {
            System.out.println("我是线程" + Thread.currentThread().getName() + "当前锁被别人占用,等待3s后仍无法获取,放弃");
        }
    }

    public static void tryLockTimeOutTest() {
        final LockTest lockTest = new LockTest();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lockTest.tryLockTimeOutTest(Thread.currentThread());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "thread1");
        //声明一个线程 “线程二”
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lockTest.tryLockTimeOutTest(Thread.currentThread());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "thread2");
        // 启动2个线程
        thread2.start();
        thread1.start();
    }

}
