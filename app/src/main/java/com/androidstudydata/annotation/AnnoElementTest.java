package com.androidstudydata.annotation;

import com.androidstudydata.LogUtils;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * Created by XR_liu on 2018/11/22.
 * AnnotatedElement相关方法的使用
 */
public class AnnoElementTest{

    @AnnoElementTest.AnnoA
    public static class A{

    }

    //B 继承A
    @AnnoElementTest.AnnoB
    public static class B extends A{

    }

    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface AnnoA {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface AnnoB {

    }

    public static void test(){
        B instanceB=new B();
        Class<?> classB=instanceB.getClass();

        //根据指定注解类型获得注解
        AnnoB annoB=classB.getAnnotation(AnnoB.class);
        LogUtils.d("根据指定注解获取注解："+annoB);

        //获取此元素上的所有注解，包括从父类继承的
        Annotation[] annotations=classB.getAnnotations();
        LogUtils.d("获取所有注解包括继承的："+ Arrays.toString(annotations));

        //获取此元素上所有注解，不包括继承的
        Annotation[] annotations1=classB.getDeclaredAnnotations();
        LogUtils.d("获取所有注解不包括继承的："+Arrays.toString(annotations1));

        //判断注解AnnoA是否在次元素上
        boolean is=classB.isAnnotationPresent(AnnoB.class);
        LogUtils.d("是否="+is);
    }
}
