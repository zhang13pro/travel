package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @Author: bobobo
 * @Date: 2019/8/4 19:15
 * @Version：1.0
 */
public interface SellerDao  {
    Seller findBySid(int sid);
}
