package com.androidstudydata.kotlin.ex

import android.arch.lifecycle.LifecycleOwner
import com.androidstudydata.kotlin.KotlinActivity
import kotlinx.coroutines.*

/**
 * Author：Mapogo
 * Date：2020/8/7
 * Note：kotlin相关扩展方法
 */

// 后台线程
internal val BackGroud = newFixedThreadPoolContext(2, "bg")

// lifecycleowner的扩展函数，可以是直接在它的实现类中调用，像activity和fragment都是有实现lifecycleowner接口的，
// 所以这个函数可以在act或fragment中直接调用的
// lifecycleowner的扩展函数，返回一个deferred，它是协程异步加载的一个返回值，可以携带想要的执行结果T
fun <T> LifecycleOwner.load(loader: () -> T): Deferred<T> {
    // deferred和job最大的不同是，前者可以在指定的时机执行协程，BackGroud是指定的线程
    val deferred = GlobalScope.async(BackGroud, CoroutineStart.LAZY) {
        // 因为用了 CoroutineStart.LAZY，只有当deferred执行了await的时候，才会执行 loader函数
        loader()
    }
    // 添加生命周期监听，当要取消的时候执行deferred的cancel方法
    lifecycle.addObserver(KotlinActivity.CoroutineLifecycleListener(deferred))
    return deferred
}

// Deferred的扩展函数，配合 load方法使用，infix代表是中缀函数，调用的时候可以不用 .号
infix fun <T> Deferred<T>.execute(block: (T) -> Unit) {
    // block方法在主线程执行
    // 这里为什么要启动一个协程，因为挂起函数 await只能在挂起函数中调用，而execute并不是挂起函数
    GlobalScope.launch(context = Dispatchers.Main) {
        // block函数接受一个T类型的参数，在图片加载中就是bitmap数据，调用await就是执行延迟的协程体
        // 即会执行上面的load（）图片加载的逻辑，他就是返回泛型 T
        // await方法返回泛型 T，
        block(this@execute.await())
    }
}