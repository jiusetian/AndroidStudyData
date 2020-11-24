package com.androidstudydata.kotlin

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.androidstudydata.LogUtils
import com.androidstudydata.R
import com.androidstudydata.Utils
import com.androidstudydata.kotlin.aboutClass.Person
import com.androidstudydata.kotlin.aboutClass.Runoob
import com.androidstudydata.kotlin.ex.BackGroud
import com.androidstudydata.kotlin.ex.load
import com.androidstudydata.kotlin.ex.execute
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.coroutines.*
import java.util.concurrent.locks.Lock

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        /**
         * lambda表达式本质上是传递一个代码块，传统的setOnClickListener参数需要传递一个匿名内部类，实现一个onClick方法，而函数式编程是
         * 支持将函数作为参数传递的，
         */
        test_tv.setOnClickListener {
            it.visibility = View.VISIBLE
        }

        /**
         * 这个是对象表达式的形式定义匿名内部类
         */
        test_tv.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

            }
        })

        // 协程测试
        coroutineTest2()
    }

    fun personTest() {

        var person: Person = Person()

        // 给属性赋值
        person.lastName = "laowang"
        println("名字是：${person.lastName}")

        person.no = 200;
        println("no的大小：${person.no}")
        // 因为height的set方法设置为private，所以不能赋值，但是可以访问get
        // person.height=333.3f
        println("高度=${person.height}")
    }

    fun runoobTest() {
        // 这里调用的是次构造函数
        var runoob: Runoob = Runoob("菜鸟教程", 1000)
        // 调用了扩展函数
        var p: Person = Person()
        p.Print()
    }

    // 创建Person类的扩展函数
    fun Person.Print() {
        println("我是person的扩展函数")
        // 在扩展函数中可以调用被扩展类的非私有属性
        method();
    }

    // 为 MutableList 扩展一个交换函数
    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val temp = this[index1] // this关键字代表接收者对象
        this[index1] = this[index2]
        this[index2] = temp

    }

    /**
     * 泛型通配符测试
     */
    fun fanxing() {

        // list 为 ArrayList<Number> 类型
        val list = arrayListOf<Number>(1, 2, 3) // 一个实参类型是Number的Collection
        // array 为 Array<Float> 类型
        val array = arrayOf(1f, 2f)
        // 因为这里上限规定了是Number，所以Float是可以的，
        val list1: List<Number> = list.plus(array)


        /*out 是一个上限通配符，Number 是元素的上限，所以Float是可以的，但是Any不行*/
        var l: List<out Number>

        l = ArrayList<Float>()

    }

    // 定义一个支持协变的类
    class Runoob1<out A>(val a: A) {
        fun foo(): A {
            return a
        }
    }

    // 定义一个支持逆变的类
    class Runoob2<in A>(a: A) {
        fun foo(a: A) {
        }
    }

    /**
     * 参数协变测试方法
     */
    fun xiebian() {

        var strCo: Runoob1<String> = Runoob1("a")
        // 这里的上限是Any，所以参数string 是可以的
        var anyCo: Runoob1<Any> = Runoob1<Any>("b")
        anyCo = strCo
        println(anyCo.foo())   // 输出 a
    }


    /**
     * 参数逆变测试
     */
    fun nibian() {
        var strDCo: Runoob2<Any> = Runoob2("ddd")
        var anyDCo = Runoob2<Any>("b")
        strDCo = anyDCo
    }


    /**
     * 加在Collection的扩展函数，这个函数是带有泛型的函数，参数是一个array集合，元素是一个泛型，out是泛型上限通配符，代表只读，
     *
     * 返回值是一个list集合，同时是一个泛型
     *
     */
    fun <T> Collection<T>.plus(elements: Array<out T>): List<T> {
        return listOf(elements.get(3))
    }

    /**
     * 枚举
     */
    enum class ProtocolState {
        WAITING {
            override fun signal() = TALKING
        },

        TALKING {
            override fun signal() = WAITING
        };

        // 这个是枚举的抽象方法
        abstract fun signal(): ProtocolState
    }

    /**
     * 第二个参数是一个函数然后返回一个T值
     */
    fun <T> check(lock: Lock, body: () -> T): T {
        lock.lock()
        try {
            return body()
        } finally {
            lock.unlock()
        }
    }


    /**
     * 协程相关
     */
    // 生命周期观察者，绑定协程的取消
    class CoroutineLifecycleListener(val deferred: Deferred<*>) : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun cancelCoroutine() {
            if (!deferred.isCancelled) {
                deferred.cancel()
            }
        }
    }

    // 利用协程加载图片演示
    fun coroutineTest() {
        // load和completed都是自定义的扩展函数
        // 当执行load的时候，会返回一个deferred，然后执行completed的时候就会执行deferred的awati函数，
        // 然后就会执行 load里面的程序并且返回一个bitmap，bitmap会传递给setImageBitmap函数并且显示出来
        load {
            LogUtils.e("当前线程：${Thread.currentThread().name}")
            LogUtils.e("执行load")
            // 图片加载任务在指定的线程中执行
            Utils.getBitmap(this, R.mipmap.ic_launcher)
            // MediaStore.Images.Media.getBitmap(contentResolver,R.mipmap.ic_launcher);
        } execute {
            imageView.setImageBitmap(it)
        }
    }

    // 其他示例
    fun coroutineTest2(){
        // 必须在主线程更新UI
        GlobalScope.launch(Dispatchers.Main) {
            // 耗时操作要在子线程，这里不阻塞主线程，所有不会有ANR
            val deferred=async(BackGroud) {
                LogUtils.e("线程1${Thread.currentThread().name}")
                Thread.sleep(7000)
                // 图片加载任务在指定的线程中执行
                Utils.getBitmap(this@KotlinActivity, R.mipmap.ic_launcher)
            }
            LogUtils.e("线程2${Thread.currentThread().name}")
            imageView.setImageBitmap(deferred.await())
        }
    }

}
