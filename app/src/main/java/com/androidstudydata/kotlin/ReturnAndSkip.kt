package com.androidstudydata.kotlin

/**
 * Author：Alex
 * Date：2019/5/9
 * Note：标签的使用
 */
class ReturnAndSkip {

    /**
     * 标签的使用
     */
    fun label() {
        for (i in 1..10) {
            loop@ for (j in 1..100) {
                //break某个标签，意味着结束某个循环，在这里是结束了第二个for循环
                if (j == 30) break@loop
                println(j)
            }
        }
    }

    /**
     * 用return直接结束了foo1方法
     */
    fun foo1() {
        listOf(1, 2, 3, 4, 5, 6).forEach {
            if (it == 3) return //次return直接结束了foo1方法
            println("打印" + it)
        }
        println("循环结束")
    }

    /**
     * 用return 返回到某个标签处去执行
     * list@是一个标签
     */
    fun foo2() {
        //在这里不会结束for循环，而是当等于3的时候，不会往下执行循环里面的内容，所以打印结果中没有数字3
        listOf(1, 2, 3, 4, 5, 6).forEach list@{
            if (it == 3) return@list
            println(it)
        }
        print("结束")
    }

    fun foo3() {
        //效果跟foo2一样
        listOf(1, 2, 3, 4, 5, 6).forEach {
            if (it == 3) return@forEach
            println(it)
        }
        print("结束")
    }


}























