package com.lib_java.compileAnnotation;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

public class CodeCreater {
    private String packageName;
    private String createrClassName;
    private TypeElement typeElement;

    //保存findViewById要用到的id值和对应的变量元素
    public Map<Integer, VariableElement> injectVariables = new HashMap<>();

    public static final String SUFFIX = "ViewInject";

    public CodeCreater(Elements elementUtils, TypeElement classElement) {
        this.typeElement = classElement;
        //获取注解元素类的全包名
        PackageElement packageElement = elementUtils.getPackageOf(classElement);
        String packageName = packageElement.getQualifiedName().toString();
        //classname
        String className = classElement.getQualifiedName().toString().substring(packageName.length() + 1);
        this.packageName = packageName;
        this.createrClassName = className + "$$" +SUFFIX;
    }


    public String generateJavaCode() {
        StringBuilder builder = new StringBuilder();
        builder.append("/* Generated code. Do not modify!*/\n");
        builder.append("package ").append(packageName).append(";\n\n"); //包路径
        builder.append("import com.lib_java.compileAnnotation.*;\n"); //导入包路径
        builder.append('\n');

        //类的声明并且继承ViewInject<T>接口，其中T是使用注解的那个类
        builder.append("public class ").append(createrClassName).append(" implements " + CodeCreater.SUFFIX +
                "<" + typeElement.getQualifiedName() + ">");
        builder.append(" {\n\n");

        generateMethods(builder);
        builder.append('\n');

        builder.append("}\n");
        return builder.toString();

    }


    private void generateMethods(StringBuilder builder) {

        //参加inject方法，实际上是实现ViewInject接口的方法
        builder.append("   @Override\n ");
        builder.append("  public void inject(" + typeElement.getQualifiedName() + " master, Object viewOwner ) {\n");

        for (int id : injectVariables.keySet()) {
            VariableElement element = injectVariables.get(id); //id对应的变量
            String name = element.getSimpleName().toString(); //变量名
            String type = element.asType().toString(); //变量类型
            //master代表使用注解的那个类的全限定名，所以master.name代表被注解的那个变量，其实也就是对应的activity对象
            builder.append("   if(viewOwner instanceof android.app.Activity){\n"); //如果master是activity
            builder.append("      master." + name).append(" = ");
            builder.append("(" + type + ")(((android.app.Activity)viewOwner).findViewById( " + id + "));\n");
            builder.append("\n   }else{\n");
            builder.append("      master." + name).append(" = ");
            builder.append("(" + type + ")(((android.view.View)viewOwner).findViewById( " + id + "));\n"); //master是View对象
            builder.append("\n    }\n");

        }
        builder.append("  }\n");


    }

    public String getCreaterClassFullName() {
        return packageName + "." + createrClassName;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }


}