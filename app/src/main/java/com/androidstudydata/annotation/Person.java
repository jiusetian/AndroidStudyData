package com.androidstudydata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 使用注解标记Person类，给注解元素赋值
 */
@Person.PersonInfo(name = "王大锤",age = 18,male = true)
public class Person {


    /**
     * 定义一个注解，包含有三个注解元素，分别代表名字、年龄、性别
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface PersonInfo{
        String name() default "";
        int age() default -1;
        boolean male() default false;
    }

    /**
     * 注解元素的简化使用方式，只定义value（）即可
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Age{
        int value() default -1;
    }


    /**
     * 注解的简化使用
     */
    @Age(20)
    class Lily{

    }
}
