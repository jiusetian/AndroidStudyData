package com.androidstudydata.annotation.runtimeanno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XR_liu on 2018/11/22.
 */
public class AnnoUtils {




    /**
     * 通过一个实体类返回一个创建数据库表的SQL语句
     *
     * @return
     */
    public static String createTableSql(Class<?> cl) {
        String sql = null;
        //获取class对象
        Class<?> clazz = cl;

        RAnnotation.Entity entity = clazz.getAnnotation(RAnnotation.Entity.class);

        String tabelName; //数据库名称
        if (entity == null || entity.tableName() == "") {
            tabelName = clazz.getSimpleName().toUpperCase();
        }

        tabelName = entity.tableName();

        //所有字段名的集合
        List<String> columNames = new ArrayList<>();

        //通过反射获取class的所有字段
        for (Field field : clazz.getDeclaredFields()) {
            String columName = null;
            //获取字段上的所有注解
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length < 1) {
                continue; //不属于表的字段
            }
            //整型字段
            if (annotations[0] instanceof RAnnotation.FeildInteger) {
                RAnnotation.FeildInteger feildInteger = (RAnnotation.FeildInteger) annotations[0];
                //给字段名赋值
                columName = "".equals(feildInteger.name()) ? field.getName().toUpperCase() : feildInteger.name();
                //保存构建SQL语句片段
                columNames.add(columName + " INT" + getContrains(feildInteger.contrain()));
            }

            //string字段
            if (annotations[0] instanceof RAnnotation.FeildString) {
                RAnnotation.FeildString feildString = (RAnnotation.FeildString) annotations[0];
                columName = "".equals(feildString.name()) ? field.getName().toUpperCase() : feildString.name();
                columNames.add(columName + " VARCHAR(" + feildString.varchar() + ")" + getContrains(feildString.contrain()));
            }


        }

        //构建数据库表语句
        StringBuilder createSql = new StringBuilder("CREATE TABLE " + tabelName + "(");

        for (String colum : columNames) {
            createSql.append("\n " + colum + ",");
        }
        sql = createSql.substring(0, createSql.length() - 1) + ")";
        return sql;
    }

    /**
     * 获取注解的相关约束值
     *
     * @param cons
     * @return
     */
    public static String getContrains(RAnnotation.Contrains cons) {
        String constraints = "";
        if (!cons.allowNull())
            constraints += " NOT NULL";
        if (cons.primaryKey())
            constraints += " PRIMARY KEY";
        if (cons.isUnique())
            constraints += " UNIQUE";
        return constraints;
    }
}
