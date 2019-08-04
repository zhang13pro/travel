package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @Author: bobobo
 * @Date: 2019/8/4 18:56
 * @Version：1.0
 */
public interface RouteImgDao {
    List<RouteImg> findByRid(int rid);
}
