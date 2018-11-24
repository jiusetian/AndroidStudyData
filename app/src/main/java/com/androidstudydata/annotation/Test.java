package com.androidstudydata.annotation;

import com.androidstudydata.LogUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by XR_liu on 2018/11/22.
 */
public class Test {

    //添加自定义注解
    @FunAnno(name="我是方法a")
    public void fun_a(){
        LogUtils.d("执行方法a");
    }

    //添加java内置的注解
    @Deprecated
    @SuppressWarnings("uncheck")
    public void fun_b(){

    }


    /**
     * 声明一个注解
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FunAnno{
        String name() default "";
    }
}
