package com.androidstudydata.kotlin.ktmap

/**
 * Author：Alex
 * Date：2019/6/27
 * Note：
 */
class KtMapTest {

    fun test(i:Int) {

        //定义一个list
        val list = listOf(1, 3, 4, 5, 6, 7, 43, 63)
        //直接用map输出
        list.map(::println) //纯粹用于迭代的话会影响性能，实现里面还有一个数组
        //public inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R> {
        //    return mapTo(ArrayList<R>(collectionSizeOrDefault(10)), transform)
        //}
        //再定义一个arrayList
        //it就是list里面的元素
        val newList = list.map {
              it * 2 + 3
        }
        //将int转成double,Int代表list中的元素，是采用类的实例去调用的
        val newList2 = list.map(Int::toDouble)

        newList.forEach(::println)
        newList2.forEach(::println)

        println("参数是=$i")
    }
}