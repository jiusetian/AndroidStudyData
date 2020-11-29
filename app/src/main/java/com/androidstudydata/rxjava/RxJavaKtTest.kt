package com.androidstudydata.rxjava

import android.annotation.SuppressLint
import com.androidstudydata.LogUtils
import io.reactivex.Observable

/**
 * Author：Alex
 * Date：2019/7/22
 * Note：
 */
object RxJavaKtTest {

    /**
     * if else 的rxjava测试
     */
    @SuppressLint("CheckResult")
    fun defaultEmptyTest() {
        Observable.just(33)
                .filter { it == 33 }
                .map { LogUtils.d("执行了第一个map") }
                .flatMap {
                    return@flatMap if (it == Unit) Observable.error<Throwable>(IllegalStateException("错误")) else Observable.just(it)
                }
                .defaultIfEmpty(Unit)
                .map { LogUtils.d("执行第二个map") }
                .subscribe({LogUtils.d("执行订阅")},{})
    }
}