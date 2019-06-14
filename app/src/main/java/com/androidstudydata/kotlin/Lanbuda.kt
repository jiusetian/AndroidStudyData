package com.androidstudydata.kotlin

import android.os.Build
import android.support.annotation.RequiresApi

/**
 * Author：Alex
 * Date：2019/6/14
 * Note：
 */
class Lanbuda {

    // 源代码
    //fun test(){ println("无参数") }

    // lambda代码
    val test = { println("无参数") }

    // 调用
    fun mainLbd() {
        //test()
        println(lbd2(2, 3))
    }

    // 源代码
    fun lbd1(a: Int, b: Int): Int {
        return a + b
    }

    // lambda
    val lbd2: (Int, Int) -> Int = { a, b -> a + b }
    // 或者
    val lbd3 = { a: Int, b: Int -> a + b }

    /**
     * it相关测试
     */
    fun itTest() {
        // 这里举例一个语言自带的一个高阶函数filter,此函数的作用是过滤掉不满足条件的值。
        val arr = arrayOf(1, 3, 5, 7, 9)
        // 过滤掉数组中元素小于2的元素，取其第一个打印。这里的it就表示每一个元素。
        //首先filter是一个Array的一个函数，这个函数的参数是一个匿名函数，匿名函数的参数是泛型T，返回一个boolean值，这个匿名函数可以用lambda表达式去实现
        //it是在当一个高阶函数中Lambda表达式的参数只有一个的时候可以使用it来使用此参数。it可表示为单个参数的隐式名称
        println(arr.filter { it < 5 }.component1())

        //lambda表达式总是将函数体包含在大括号内，如果有2个以上参数，那么->前面代表参数，后面代表函数体，最后一行函数可以代表返回值，如果只有1个参数，那么可以用it来代替
        println(gaojie(10, { it > 5 }))
        println(gaojie(4, { it > 5 }))
    }

    /**
     * 因为gaojie函数的参数有一个是函数，所以gaojie就是高阶函数，而这个参数函数只有一个Int类型的参数，所以在实现的时候，可以用it代替这个参数
     */
    fun gaojie(num1: Int, bool: (Int) -> Boolean): Int {
        return if (bool(num1)) {
            num1
        } else 0
    }

    /**
     * 下划线（_）
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun xaihuaxian() {
        //mapOf是一个函数，参数是Pair类型，返回Map对象
        //其中to是一个中缀函数，infix 自定义运算符的中缀表达式，不需要类名方法即可调用
        val p = 33 to 33 //其中p是一个Pair实例，定义如：public infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)
        val map = mapOf("key1" to "value1", "key2" to "value2", "key3" to "value3")
        //forEach是Java的函数，其中参数是一个接口，可以传入一个匿名函数，所以也是可以用lambda来实现的
        map.forEach { key, value ->
            println("$key \t $value")
        }

        // 不需要key的时候
        map.forEach { _, value ->
            println("$value")
        }
    }

    /**
     * 匿名函数
     */
    fun niming() {
        val test1 = fun(x: Int, y: Int) = x + y  // 当返回值可以自动推断出来的时候，可以省略，和函数一样
        val test2 = fun(x: Int, y: Int): Int = x + y
        val test3 = fun(x: Int, y: Int): Int {
            return x + y
        }

        println(test1(3, 5))
        println(test2(4, 6))
        println(test3(5, 7))
    }

}