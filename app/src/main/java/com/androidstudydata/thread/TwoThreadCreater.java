package com.androidstudydata.thread;

/**
 * Author：Alex
 * Date：2019/11/26
 * Note：两种线程启动方式
 */
public class TwoThreadCreater {


    public  void executeOneRun()
    {
        Ticket t = new Ticket();

        Thread win1 = new Thread(t);
        Thread win2 = new Thread(t);
        Thread win3 = new Thread(t);
        Thread win4 = new Thread(t);

        win1.start();
        win2.start();
        win3.start();
        win4.start();
    }

    //这个类只是为了描述线程的任务，跟线程没有任何关系。
    class Ticket implements Runnable
    {
        private int num = 50;

        @Override
        public void run()
        {
            while(num>0)
                System.out.println(Thread.currentThread().getName()+"...sale..."+num--);
        }
    }
}
