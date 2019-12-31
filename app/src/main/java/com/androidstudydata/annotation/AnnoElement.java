package com.androidstudydata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by XR_liu on 2018/11/22.
 * 注解元素相关
 */
public class AnnoElement {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface AnnoRef{
        int value() default -1;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface AnnoElements{

        //定义性别枚举
        enum Gender{MALE,FEMALE}

        //声明枚举
        Gender gender() default Gender.FEMALE;

        //声明string
        String name() default "";

        //声明int
        int age() default -1;

        //声明class类型
        Class<?> Person() default Void.class;

        //注解嵌套
        AnnoRef ref() default @AnnoRef;

        //数组类型
        String[] strs() default {""};
    }


    /**
     * 注解的应用，其中@AnnoElements代表一个注解类型，括号里面代表这个注解的属性和赋值，其实注解可以理解为Java中某个类或字段的
     * 标识，然后这个标识可能附带一些必要的信息，在Java编译或运行阶段拿来使用
     */
    @AnnoElements(gender = AnnoElements.Gender.MALE,name = "java",
            age = 100,Person = Person.class,ref = @AnnoRef(10),strs = {"a","b"})
    class Test{
    }
}
