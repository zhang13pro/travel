package cn.itcast.demo.po;

/**
 * @Author: bobobo
 * @Date: 2019/8/5 16:48
 * @Version：1.0
 */
public class Student {
    private int age;
    private String name;

    public void study(){
        System.out.println("xuexuexeu=====");
    }

    public Student(){};

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
