package com.androidstudydata.annotation.runtimeanno;

/**
 * Created by XR_liu on 2018/11/22.
 */
@RAnnotation.Entity
public class Person {

    //主键
    @RAnnotation.FeildString(contrain = @RAnnotation.Contrains(primaryKey = true))
    private String id;

    @RAnnotation.FeildString
    private String name;

    @RAnnotation.FeildInteger
    private int age;

    //允许为null
    @RAnnotation.FeildString(contrain = @RAnnotation.Contrains(allowNull = true))
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
