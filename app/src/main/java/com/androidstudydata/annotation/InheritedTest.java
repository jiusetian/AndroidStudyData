package com.androidstudydata.annotation;

import com.androidstudydata.LogUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * Created by XR_liu on 2018/11/22.
 */
public class InheritedTest {


    /**
     * 被Inherited修饰的注解
     */
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface AnnoA {

    }

    /**
     * 没有Inherited修饰的注解
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface AnnoB {

    }

    @AnnoA
    class A{
    }

    class B extends A{

    }

    @AnnoB
    class C{

    }

    class D extends C{

    }

    public  void test(){
//        A instanceA=new A();
//        D instanceD=new D();

        LogUtils.d("使用了Inherited注解的情况："+ Arrays.toString(B.class.getAnnotations()));
        LogUtils.d("没有使用了Inherited注解的情况："+ Arrays.toString(D.class.getAnnotations()));
    }

}
