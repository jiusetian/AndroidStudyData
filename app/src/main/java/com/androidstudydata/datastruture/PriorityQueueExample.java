package com.androidstudydata.datastruture;

import com.easysocket.utils.LogUtil;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Author：Alex
 * Date：2019/12/13
 * Note：优先队列
 * PriorityQueue默认实现的是最小堆，就是说你的compare(T o1, T o2)实现中如果o1大于o2返回1，o1==o2返回0，o1小于o2返回-1的情况下，实现的是最小堆，poll
 * 方法返回的是堆中的最小值，如果你要实现最大堆，就要将compare(T o1, T o2)的实现跟上面的实现相反
 */
public class PriorityQueueExample {

    //匿名Comparator实现
    public static Comparator<Customer> customerComparator = new Comparator<Customer>() {
        @Override
        public int compare(Customer o1, Customer o2) {
            //返回正数代表o1比o2大，这种写法优先队列中实现的就是最大堆了，即你最先取出的是最大值
            return -(o1.getId() - o2.getId());
        }
    };

    public static void test() {

        //优先队列自然排序，比如整数的话会从小到大输出，
        Queue<Integer> integerQueue = new PriorityQueue<>();

        for (int i = 10; i > 0; i--) {
            integerQueue.add(new Integer(i));
        }

        for (int i = 0; i < 10; i++) {
            LogUtil.d("优先队列的整数输出=" + integerQueue.poll());
        }

        Queue<Customer> customerQueue = new PriorityQueue<>(10, customerComparator);

        addDataToQueue(customerQueue);

        pollDataFromQueue(customerQueue);
    }

    //用于往队列增加数据的通用方法
    private static void addDataToQueue(Queue<Customer> customerPriorityQueue) {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int id = rand.nextInt(1000);
            customerPriorityQueue.add(new Customer(id, "刘兴荣" + id));
            LogUtil.d("存入的刘兴荣id=" + id);
        }
    }

    //用于从队列取数据的通用方法
    private static void pollDataFromQueue(Queue<Customer> customerpriorityQueue) {
        while (true) {
            Customer ctm = customerpriorityQueue.poll();
            if (ctm == null)
                break;
            System.out.println("取出的刘兴荣id =" + ctm.getId());
        }
    }


    private static class Customer {
        private int id;
        private String name;

        public Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
