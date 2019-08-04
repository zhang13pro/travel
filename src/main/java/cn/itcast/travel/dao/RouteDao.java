package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @Author: bobobo
 * @Date: 2019/8/3 15:43
 * @Version：1.0
 */
public interface RouteDao {
    //* 根据cid查询总记录数
    int findTotalCount(int cid);
    //根据cid  start pageSize 查询当前页的数据集合
    List<Route> findByPage(int cid,int start,int pageSize);
}
