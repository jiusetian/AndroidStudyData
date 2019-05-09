package com.androidstudydata.kotlin

/**
 * Author：Alex
 * Date：2019/5/9
 * Note：
 */
class ReturnAndSkip {

    /**
     * 标签的使用
     */
    fun label() {
        //两个for循环，当第二个for循环到30的时候，将不再进行下去，而是返回第一个for循环继续下一个循环
        loop@ for (i in 1..100) {
            for (j in 1..100) {
                //直接
                if (j == 30) break@loop //类似于continue
                println(j)
            }
        }
    }

    /**
     * 用return直接结束了foo1方法
     */
    fun foo1() {
        listOf(1, 2, 3, 4, 5, 6).forEach {
            if (it == 3) return
            println(it)
        }

        println("循环结束")
    }

    /**
     * 用return 返回到某个标签处去执行
     * list@是一个标签
     */
    fun foo2() {
        //在这里不会结束for循环，而是当等于3的时候，不会往下执行循环里面的内容
        listOf(1, 2, 3, 4, 5, 6).forEach list@{
            if (it == 3) return@list
            println(it)
        }
        print("结束")
    }

    fun foo3() {
        listOf(1, 2, 3, 4, 5, 6).forEach {
            if (it == 3) return@forEach
            println(it)
        }
        print("结束")
    }


}























