package cn.itcast.demo.po;

/**
 * @Author: bobobo
 * @Date: 2019/8/5 14:58
 * @Version：1.0
 */
public class Person {
    private String name;
    private int age;

    public Person(){};

    public void eat(){
        System.out.println("chichihchci===");
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
}
