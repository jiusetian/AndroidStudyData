package com.androidstudydata.kotlin.aboutClass

/**
 * Author：Alex
 * Date：2019/6/13
 * Note：匿名内部类
 */
class NimingClass {

    class Test {
        var v = "成员属性"

        fun setInterFace(test: TestInterFace) {
            test.test()
        }
    }

    /**
     * 定义接口
     */
    interface TestInterFace {
        fun test(): Int
    }

    fun niming() {
        var test = Test()
        test.setInterFace(object : TestInterFace {
            override fun test(): Int {
                return 22
            }
        })
    }
}