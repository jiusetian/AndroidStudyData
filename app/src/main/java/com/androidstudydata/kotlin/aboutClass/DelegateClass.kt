package com.androidstudydata.kotlin.aboutClass

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Author：Alex
 * Date：2019/6/14
 * Note：kotlin委托类
 */

//***************************类委托************************

// 创建接口
interface Base {
    fun print()
}

// 实现此接口的被委托的类
class BaseImpl(val x: Int) : Base {
    override fun print() {
        print(x)
    }
}

// 通过关键字 by 建立委托类
class Derived(b: Base) : Base by b

fun main1() {
    //BaseImpl是被委托的类，而Derived是委托类，外界通过调用委托类的方法间接调用被委托类的函数
    val b = BaseImpl(10)
    Derived(b).print() // 输出 10
}


//**********************属性委托**************************

// 定义包含属性委托的类
class Example {
    //这个属性p被Delegate委托
    var p: String by Delegate()
}

// 委托的类
class Delegate {

    /**
     * thisRef:代表被委托类
     * property：代表被委托的属性
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, 这里委托了 ${property.name} 属性"
    }

    /**
     * value:代表被委托属性的值
     */
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef 的 ${property.name} 属性赋值为 $value")
    }

}

fun main2() {
    val e = Example()
    //输出：Example@433c675d, 这里委托了 p 属性
    println(e.p)     // 访问该属性，调用 getValue() 函数

    // 输出：Example@433c675d 的 p 属性赋值为 Runoob
    e.p = "Runoob"   // 调用 setValue() 函数

    //输出：Example@433c675d, 这里委托了 p 属性
    println(e.p)

    println("打印")
    "后面的"
}

//===============================标准委托==========================

/**
 * 延迟属性lazy，第一次
 */
val lazyValue: String by lazy {
    println("computed!")     // 第一次调用输出，第二次调用不执行
    "Hello"
}

//值的初始化值在括号内
val lazyValue2: Int by lazy {
    333
}

fun main3() {
    println(lazyValue)   // 第一次执行，执行两次输出表达式
    println(lazyValue)   // 第二次执行，只输出返回值


    //第一次执行
//    computed!
//    Hello
    //第二次执行
//    Hello
}

/**
 * 可观察属性 Observable
 */

class User {
    //当属性发生变化的时候，是可以观察到的
    var name: String by Delegates.observable("初始值") { prop, old, new ->
        println("旧值：$old -> 新值：$new")
    }
}

fun main4() {
    val user = User()
    user.name = "第一次赋值"
    user.name = "第二次赋值"
}

/**
 * 把属性储存在映射中
 * 使用映射实例自身作为委托来实现委托属性。
 * 如果使用 var 属性，需要把 Map 换成 MutableMap
 */
class Site(val map: Map<String, Any?>) {
    val name: String by map
    val url: String  by map
}

fun main5() {
    // 构造函数接受一个映射参数
    // mapOf接受一个可变参数
    val site = Site(mapOf(
            //public infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)
            //这里的to相当于返回了一个Pair（this，that）对象，其中this是指A，that是B
            "name" to "菜鸟教程",
            "url" to "www.runoob.com"
    ))


    // 读取映射值
    println(site.name)
    println(site.url)
}

class Site2(val map: MutableMap<String, Any?>) {
    val name: String by map
    val url: String by map
}

fun main155() {

    var map: MutableMap<String, Any?> = mutableMapOf(
            "name" to "菜鸟教程",
            "url" to "www.runoob.com"
    )

    val site = Site(map)

    println(site.name)
    println(site.url)

    println("--------------")
    map.put("name", "Google")
    map.put("url", "www.google.com")

    println(site.name)
    println(site.url)

}

/**
//翻译规则class C 将属性prop委托给MyDelegate，意味着MyDelegate需要实现getValue和setValue方法

class C {
var prop: Type by MyDelegate()
}

// 这段是由编译器生成的相应代码：
class C {
private val prop$delegate = MyDelegate()
var prop: Type
get() = prop$delegate.getValue(this, this::prop)
set(value: Type) = prop$delegate.setValue(this, this::prop, value)
}

 */














