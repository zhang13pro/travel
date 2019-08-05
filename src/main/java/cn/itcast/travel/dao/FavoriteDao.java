package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

/**
 * @Author: bobobo
 * @Date: 2019/8/5 8:21
 * @Version：1.0
 */
public interface FavoriteDao {
    Favorite findByRidAndUid(int rid, int uid);
}
