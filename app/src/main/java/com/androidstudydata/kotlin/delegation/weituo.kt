package com.androidstudydata.kotlin.delegation

/**
 * Author：Alex
 * Date：2019/6/26
 * Note：kotlin委托相关
 */

//代购的接口
interface Buyer{
    fun buyCoach()

    fun buyGucci()
}

class XiaoMing : Buyer{
    override fun buyCoach() {
        println("XiaoMing buy Coach!")
    }

    override fun buyGucci() {
        println("XiaoMing buy Gucci!")
    }
}

/**
 * 指定委托关系
 */
class Xiaohong(realBuyer: Buyer):Buyer by realBuyer



