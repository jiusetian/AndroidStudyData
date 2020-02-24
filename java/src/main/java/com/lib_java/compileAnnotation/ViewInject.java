package com.lib_java.compileAnnotation;

/**
 * Created by XR_liu on 2018/11/24.
 */
public interface ViewInject<T> {
    //T是指使用注解的类，viewOwner是注解view的持有者
    void inject(T t, Object viewOwner);
}
