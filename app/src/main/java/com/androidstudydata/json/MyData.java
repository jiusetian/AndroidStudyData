package com.androidstudydata.json;

/**
 * Author：Alex
 * Date：2019/5/31
 * Note：
 */
public class MyData implements IReceive {
    private int D;
    private String Name;
    private int Age;



    public int getID() {
        return D;
    }

    public void setID(int ID) {
        this.D = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    @Override
    public int getMID() {
        return D;
    }
}
