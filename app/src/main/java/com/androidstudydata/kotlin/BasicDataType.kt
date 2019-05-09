package com.androidstudydata.kotlin

/**
 * Author：Alex
 * Date：2019/5/9
 * Note：基本数据类型
 */
class BasicDataType {

    /**
     * 比较两个数字
     * ===表示比较对象地址，==表示比较值
     */
    fun compare() {
        val a: Int = 1000;
        print(a === a);

        //下面的经过封箱，两个不同的对象
        val boxA: Int = a;
        val boxB: Int = a;

        print(boxA === boxB); //对象地址不一样
        print(boxA == boxB); //值是一样的
    }

    /**
     * 数组
     */
    fun arr() {
        //数组定义
        val a = arrayOf(1, 2, 3);
        //
        val b = Array(4, { i -> (i * 2) })

        val asc = Array(5) { i -> (i * i).toString() }
        asc.forEach { print(it) }

    }

}



























