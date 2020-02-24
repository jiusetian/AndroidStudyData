package com.lib_java.compileAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by XR_liu on 2018/11/22.
 * 运行时注解
 */
public class RAnnotation {


    /**
     * 约束注解
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Contrains{

        //是否为主键
        boolean primaryKey() default false;

        //是否允许为null
        boolean allowNull() default false;

        //是否唯一
        boolean isUnique() default false;
    }

    /**
     * 实体类注解
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Entity{
        String tableName() default "";
    }

    /**
     * 整型字段
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FeildInteger{
        //对应的字段名
        String name() default "";
        //嵌套注解
        Contrains contrain() default @Contrains;
    }

    /**
     * 字符串字段
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FeildString{

        String name() default "";

        int varchar() default 30;

        Contrains contrain() default @Contrains;
    }

}
