package com.androidstudydata.kotlin.coroutines

import android.util.Log
import com.androidstudydata.LogUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Author：Mapogo
 * Date：2020/8/6
 * Note：协程相关测试
 */
object CoroutineTest {

    fun cancelTest() {
        val job = GlobalScope.launch {
            LogUtils.e("协程启动")
            // 如果delay就可以被取消
            Thread.sleep(3000)
            LogUtils.e("执行协程")
        }

        Thread.sleep(2000)
        // 取消协程
        job.cancel()
        LogUtils.e("取消协程")
    }

    fun cancleTest2() = runBlocking {

//        val job1 = launch(Dispatchers.Default) {
//            repeat(5) {
//                println("job1 sleep ${it + 1} times")
//                delay(500)
//            }
//        }
//        delay(700)
//        job1.cancel()

        val job2 = launch(Dispatchers.Default) {
            var nextPrintTime = 0L
            var i = 1
            // 在协程已经启动的情况下，即使cancel了，还是会继续执行， 除非有isActive的判断
            while (i <= 10 && isActive) {
                val currentTime = System.currentTimeMillis()
                //
//                if (currentTime >= nextPrintTime) {
//                    println("job2 sleep ${i++} ...")
//                    nextPrintTime = currentTime + 500L
//                }
                Thread.sleep(200)
                i++
                LogUtils.e("执行=$currentTime")
            }
        }

        delay(700)
        job2.cancel()
    }


    fun launchThreadTest() {

        // 启动协程 1
        GlobalScope.launch {
            println("the first coroutine")
            // 经测试，两个协程运行在不同的线程上
            println("线程11=${Thread.currentThread().name}")
            delay(200)
            println("the first coroutine")
        }

        // 启动协程 2
        GlobalScope.launch {
            println("the second coroutine")
            println("线程22=${Thread.currentThread().name}")
            delay(100)
            println("the second coroutine")
        }
    }


    // 这样写是线程安全的
    fun mutexTest() {
        val mutex = Mutex()
        var i = 0
        val job1 = GlobalScope.launch {
            // 这个相当于多线程的同步锁，当有协程进入这个锁以后，其他协程是不能进入这个锁的，只有
            // 等这个协程释放了锁才能拿到执行权
            mutex.withLock {
                LogUtils.e("进入job1")
                repeat(1000) {
                    i++
                }
            }
            LogUtils.e("当前值1=$i")
        }

        val job2 = GlobalScope.launch {
            mutex.withLock {
                LogUtils.e("进入job2")
                repeat(2000) {
                    i++
                }
            }
            LogUtils.e("当前值2=$i")
        }
    }

    fun synTest() {
        // 经测试这样的写法是线程不安全的
        var i = 0
        val job1 = GlobalScope.launch {
            repeat(1000) {
                i++
            }
            LogUtils.e("当前值1=$i")
        }

        val job2 = GlobalScope.launch {
            repeat(2000) {
                i++
            }
            LogUtils.e("当前值2=$i")
        }
    }

    // 协程测试
    fun coroutineHangup() {

        // 如果指定了在main线程中执行，则肯定会阻塞UI
        GlobalScope.launch(context = Dispatchers.Main) {
            withContext(context = Dispatchers.Unconfined) {
                // 这里并不会阻塞主线程，而是在另外一个线程中执行
                repeat(1000) {
                    LogUtils.e("协程执行${Thread.currentThread().name},$it")
                }
            }
        }

        LogUtils.e("执行主线程")
    }

    // 创建协程
    fun createCoroutine() {
        Log.d("AA", "协程初始化开始，时间: " + System.currentTimeMillis())
        // 以launch方法创建协程，因为是单线程，程序会按顺序执行，但是中间的delay是不会阻塞主线程的，
        // 程序直接回跳过继续往下执行，当delay时间到了，又会回来继续执行协程任务

//        可以把 Job 看成协程对象本身，协程的操作方法都在 Job 身上了
//        job.start() - 启动协程，除了 lazy 模式，协程都不需要手动启动
//        job.join() - 等待协程执行完毕
//        job.cancel() - 取消一个协程
//        job.cancelAndJoin() - 等待协程执行完毕然后再取消

        var job = GlobalScope.launch(Dispatchers.Unconfined) {
            // 里面算是协程体，其实是用suspend修饰无参无返回值的函数，所以这里可以用兰布达表达式来实现
            // suspend修饰的函数是挂起函数，也就是说挂起函数会按顺序执行，前面的还没执行完绝对不会执行
            // 后面的
            Log.d("AA", "协程初始化完成，时间: " + System.currentTimeMillis())
            for (i in 1..3) {
                Log.d("AA", "协程任务1打印第$i 次，时间: " + System.currentTimeMillis())
            }
            // 不会阻塞主线程
            delay(500)

            for (i in 1..3) {
                Log.d("AA", "协程任务2打印第$i 次，时间: " + System.currentTimeMillis())
            }
        }

        Log.d("AA", "主线程 sleep ，时间: " + System.currentTimeMillis())
        Thread.sleep(1000)

        Log.d("AA", "主线程运行，时间: " + System.currentTimeMillis())
        for (i in 1..3) {
            Log.d("AA", "主线程打印第$i 次，时间: " + System.currentTimeMillis())
        }
    }

    // 协程挂起函数的测试
    fun suspendTest() {
        GlobalScope.launch {
            val token = getToken()
            val userInfo = getUserInfo(token)
            setUserInfo(userInfo)
        }

        repeat(8) {
            LogUtils.e("主线程执行$it")
        }
    }

    // 执行顺序的测试，这个执行顺序没有固定的规律，经测试先执行主线程的函数，但是主线程执行到一定的时候，又会
    // 执行协程体的函数，然后相间执行
    fun excuteTest() {

        val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
            repeat(10) {
                LogUtils.e("协程体的执行$it")
            }
        }

        repeat(100) {
            LogUtils.e("主线程的执行$it")
        }

        job.start()
    }

    // 协程异步测试
    fun asyncTest2(){
        val deferred = GlobalScope.async {
            delay(1000L)
            Log.d("AA", "This is async ")
            return@async "返回值"
        }
        // 挂起函数只能在挂起函数中调用，因为这里是主线程，所以是不能调用挂起函数的
        //val result=deferred.await()
    }

    fun asyncTest() {

        // 主协程
        GlobalScope.launch {
            // async一般用在有返回值的协程体，而 launch是没有返回值的，这就是他们两的主要区别
            val deferred = GlobalScope.async {
                delay(1000L)
                Log.d("AA", "This is async ")
                return@async "返回值"
            }

            Log.d("AA", "协程 other start")
            // 在这里执行async的协程体，不会阻塞主线程
            // 因为launch里面是一个挂起函数，才可以执行挂起函数await
            val result = deferred.await()
            Log.d("AA", "async result is $result")
            Log.d("AA", "协程 other end ")
        }
        // 一般比协程早执行
        Log.d("AA", "主线程位于协程之后的代码执行，时间:  ${System.currentTimeMillis()}")
    }

    // 协程挂起测试，一般协程体一旦开始执行，就要执行完才会再执行其他的协程体，但是调用了delay的话，就会继续往下执行
    fun hangup() {

        // Dispatchers.Unconfined：在调用的线程直接执行
        // Dispatchers.Default：这个调度器经过优化，可以在主线程之外执行 cpu 密集型的工作
        // Dispatchers.Main：使用这个调度器在 Android 主线程上运行一个协程。可以用来更新UI，在UI线程中执行
        // Dispatchers.IO：这个调度器被优化在主线程之外执行磁盘或网络 I/O，在线程池中执行
        // 例如对列表进行排序和解析 JSON。在线程池中执行
        GlobalScope.launch(Dispatchers.Unconfined) {

            // 当调度器使用default的时候，因为是经过优化的，可以在主线程外执行，所以协程体的执行和其他的执行
            // 可以是并发的，而使用unconfined的时候，因为只有在调度线程执行，是单线程的，所以就只有协程体执行完
            // 才会执行其他协程体
            repeat(100) {
                Log.e("AA", "协程任务打印第$it 次，时间: ${System.currentTimeMillis()}")

            }

        }

        repeat(100) {
            Log.e("AA", "主线程打印第$it 次，时间:  ${System.currentTimeMillis()}")
        }

    }

    private fun setUserInfo(userInfo: String) {
        LogUtils.e(userInfo)
    }

    private suspend fun getToken(): String {
        delay(2000)
        return "token"
    }

    private suspend fun getUserInfo(token: String): String {
        delay(2000)
        return "$token - userInfo"
    }
}