package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

/**
 * @Author: bobobo
 * @Date: 2019/8/5 8:20
 * @Version：1.0
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao dao = new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = dao.findByRidAndUid(Integer.parseInt(rid), uid);
        return favorite != null; //有值则为true
    }
}
