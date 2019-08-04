package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * @Author: bobobo
 * @Date: 2019/8/3 15:39
 * @Version：1.0
 */
public interface RouteService {
    PageBean<Route> pageQuery(int cid,int currentPage,int pageSize);
}
