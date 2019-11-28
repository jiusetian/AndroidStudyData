package com.androidstudydata.kotlin

/**
 * Author：Alex
 * Date：2019/8/4
 * Note：集合高级操作符
 */
object CollectionOperator {

    /**
     * 映射操作符
     */
    fun mapOperator(){

    }

    /**
     * 元素操作符
     */
    fun elementOperator() {
        val list = listOf("kotlin", "Android", "Java", "PHP", "Python", "IOS")

        println("================elementAt==================")
        //第二个参数是一个lambda函数，其中这里的it是他的参数值，这里的意思是返回it，从源码来看第二个函数的参数就是第一个参数的值
        println(list.elementAtOrElse(10, { it }))

        println("================get===================")
        //跟elementAtOrElse差不多
        println(list.getOrElse(7, { it }))

        println("================first===================")
        //遍历集合，找到第一个满足条件的元素，在这里即第一个满足等于Android的元素，如果没有则抛异常
        println(list.first { it == "Android" })
        println(list.firstOrNull{it=="dddd"})

        println("================last===================")
        //遍历集合，找到最后一个满足条件的元素，在这里即最后一个满足等于Android的元素，如果没有则抛异常
        println(list.last { it=="Android" })

        println("================indexOf===================")
        //找到第一个满足条件的元素的下标值
        println(list.indexOfFirst { it=="Android" })

        println("================single===================")


        println("================forEach===================")
        list.forEach { println(it) }
        list.forEachIndexed { index, s -> println("index:$index,value:$s")  }

        println("================componentX===================")
        println(list.component1())
    }
}










































