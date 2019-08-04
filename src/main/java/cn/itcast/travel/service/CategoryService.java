package cn.itcast.travel.service;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @Author: bobobo
 * @Date: 2019/8/1 19:08
 * @Version：1.0
 */
public interface CategoryService {

    List<Category> findAll();
}
