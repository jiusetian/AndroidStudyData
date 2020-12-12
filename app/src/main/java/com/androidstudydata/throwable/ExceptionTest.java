package com.androidstudydata.throwable;

import com.androidstudydata.LogUtils;

import java.util.concurrent.TimeUnit;

/**
 * Author：Mapogo
 * Date：2020/12/11
 * Note：
 */
public class ExceptionTest {

    /**
     * 测试在while循环里面throw 异常是否结束循环，RuntimeException异常程序直接崩溃了
     */
    public static void whileTest() {
        boolean isLoop = true;
        while (isLoop) {
            LogUtils.d("内循环");
            try {
                TimeUnit.MILLISECONDS.sleep(3);
                throw new RuntimeException("跑出异常");
            } catch (InterruptedException e) {
                e.printStackTrace();
                isLoop = false;
                LogUtils.d("异常捕获");
            }
        }
    }

    /**
     * 非运行时异常Exception，需要try catch捕获，可在catch里面设置退出循环
     */
    public static void whileTest2() {
        boolean isLoop = true;
        while (isLoop) {
            LogUtils.d("内循环");
            try {
                TimeUnit.MILLISECONDS.sleep(3);
                throw new Exception("非运行时异常");
            } catch (Exception e) {
                e.printStackTrace();
                isLoop = false;
                LogUtils.d("异常捕获");
            }
        }
    }

    public static void whileTest3(){
        while (true) {
            LogUtils.d("内循环");
            try {
                TimeUnit.MILLISECONDS.sleep(3);
                byte[] bufArray = new byte[1024 * 4]; // 从服务器单次读取的最大数据
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.d("异常捕获");
            }
        }
    }
}
