package com.lib_java.dynamicProxy;

/**
 * Created by XR_liu on 2018/11/12.
 */
public class MonitorUtil {

    private static ThreadLocal<Long> tl = new ThreadLocal<>();

    public static void start() {
        tl.set(System.currentTimeMillis());
    }

    //结束时打印耗时
    public static void finish(String methodName) {
        long finishTime = System.currentTimeMillis();
        System.out.print(methodName + "方法耗时" + (finishTime - tl.get()) + "ms");
    }
}
