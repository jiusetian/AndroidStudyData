package com.androidstudydata.thread;

/**
 * Author：Alex
 * Date：2019/11/26
 * Note：循环式的死锁
 * 在这个例子中，形成了一个锁依赖的环路。以t1为例，它先将第一个对象锁住，但是当它试着向第二个对象获取锁时，它就会进入等待状态，因为第二个对象已经被
 * 另一个线程锁住了。这样以此类推，t1依赖t2锁住的对象obj2，t2依赖t3锁住的对象obj3，而t3依赖t1锁住的对象obj1，从而导致了死锁。在线程引起死锁的过程中，就
 * 形成了一个依赖于资源的循环
 */
public class CycleDeadLock implements Runnable {

    private Object obj1;
    private Object obj2;

    public CycleDeadLock(Object o1, Object o2) {
        this.obj1 = o1;
        this.obj2 = o2;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        //锁1进入以后要结束必须依赖于拿到锁2，如果锁2被占用了，那么锁1也就不会结束，这是产生死锁的根本原因
        //如果你运行一个锁从开始到结束不依赖于其他锁的话，几乎不可能出现一个死锁的局面
        synchronized (obj1) {
            System.out.println(name + " acquired lock on " + obj1);
            work();
            synchronized (obj2) {
                System.out.println("After, " + name + " acquired lock on " + obj2);
                work();
            }
            System.out.println(name + " released lock on " + obj2);
        }
        System.out.println(name + " released lock on " + obj1);
        System.out.println(name + " finished execution.");
    }

    private void work() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //测试
    public static void test() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();

        Thread t1 = new Thread(new CycleDeadLock(obj1, obj2), "t1");
        Thread t2 = new Thread(new CycleDeadLock(obj2, obj3), "t2");
        Thread t3 = new Thread(new CycleDeadLock(obj3, obj1), "t3");

        try {
            t1.start();
            Thread.sleep(1000);
            t2.start();
            Thread.sleep(1000);
            t3.start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
