package com.lib_java.compileAnnotation;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/**
 * Created by XR_liu on 2018/11/23.
 */
public class SqlUtils {

    public static String creaeteTableSql(String tableName, List<VariableElement> variableElements) {
        String sql;
        //所有字段名的集合
        List<String> columNames = new ArrayList<>();

        //循环获取注解相关信息
        for (VariableElement element : variableElements) {
            String columName = null;
            RAnnotation.FeildInteger IntAnno = element.getAnnotation(RAnnotation.FeildInteger.class);
            RAnnotation.FeildString stringAnno = element.getAnnotation(RAnnotation.FeildString.class);

            //整型字段
            if (IntAnno != null) {
                //给字段名赋值
                columName = "".equals(IntAnno.name()) ? element.getSimpleName().toString().toUpperCase() : IntAnno.name();
                //保存构建SQL语句片段
                columNames.add(columName + " INT" + getContrains(IntAnno.contrain()));
            }

            //string字段
            if (stringAnno != null) {
                columName = "".equals(stringAnno.name()) ? element.getSimpleName().toString().toUpperCase() : stringAnno.name();
                columNames.add(columName + " VARCHAR(" + stringAnno.varchar() + ")" + getContrains(stringAnno.contrain()));
            }
        }

        //构建数据库表语句
        StringBuilder createSql = new StringBuilder("CREATE TABLE " + tableName + "(");

        for (String colum : columNames) {
            createSql.append("\n " + colum + ",");
        }
        sql = createSql.substring(0, createSql.length() - 1) + ")";
        return sql;
    }


    public static String createTableSql(TypeElement classElement) {
        String sql = null;
        String tableName; //表名称
        RAnnotation.Entity entity = classElement.getAnnotation(RAnnotation.Entity.class);
        if (entity == null || entity.tableName() == "") {
            tableName = classElement.getSimpleName().toString().toUpperCase();
        } else {
            tableName = classElement.getSimpleName().toString().toUpperCase();
        }
        //所有带注解的字段
        List<VariableElement> variableElements = getVariableElements(classElement);
        //所有字段名的集合
        List<String> columNames = new ArrayList<>();

        //循环获取注解相关信息
        for (VariableElement element : variableElements) {
            String columName = null;
            RAnnotation.FeildInteger IntAnno = element.getAnnotation(RAnnotation.FeildInteger.class);
            RAnnotation.FeildString stringAnno = element.getAnnotation(RAnnotation.FeildString.class);

            //整型字段
            if (IntAnno != null) {
                //给字段名赋值
                columName = "".equals(IntAnno.name()) ? element.getSimpleName().toString().toUpperCase() : IntAnno.name();
                //保存构建SQL语句片段
                columNames.add(columName + " INT" + getContrains(IntAnno.contrain()));
            }

            //string字段
            if (stringAnno != null) {
                columName = "".equals(stringAnno.name()) ? element.getSimpleName().toString().toUpperCase() : stringAnno.name();
                columNames.add(columName + " VARCHAR(" + stringAnno.varchar() + ")" + getContrains(stringAnno.contrain()));
            }
        }

        //构建数据库表语句
        StringBuilder createSql = new StringBuilder("CREATE TABLE " + tableName + "(");

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

    //获取类下面所有带注解的字段
    private static List<VariableElement> getVariableElements(TypeElement typeElement) {

        List<Element> elements = (List<Element>) typeElement.getEnclosedElements();
        List<VariableElement> vlist = new ArrayList<>();
        for (Element element : elements) {
            if (element.getKind().isField() && (element.getAnnotation(RAnnotation.FeildInteger.class) != null ||
                    element.getAnnotation(RAnnotation.FeildString.class) != null)) {
                vlist.add((VariableElement) element);
            }
        }
        return vlist;
    }
}


















