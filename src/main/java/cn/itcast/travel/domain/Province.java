package cn.itcast.travel.domain;

/**
 * @Author: bobobo
 * @Date: 2019/8/3 10:49
 * @Version：1.0
 * 案例需求：
 * 	1. 提供index.html页面，页面中有一个省份 下拉列表
 * 	2. 当 页面加载完成后 发送ajax请求，加载所有省份
 * redis&ajax
 */
public class Province {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
