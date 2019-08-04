package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Province;

import java.util.List;

/**
 * @Author: bobobo
 * @Date: 2019/8/3 10:57
 * @Version：1.0
 */
public interface ProvinceDao {
    public List<Province> findAll();
}
