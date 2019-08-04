package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @Author: bobobo
 * @Date: 2019/8/1 18:59
 * @Version：1.0
 */
public interface CategoryDao {
    List<Category> findAll();
}
