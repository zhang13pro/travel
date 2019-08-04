package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: bobobo
 * @Date: 2019/8/1 19:10
 * @Version：1.0
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        //1缓存优化 从redis查询数据
        //1.1获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2排序查询
//        Set<String> categorys = jedis.zrange("category", 0, -1);
        //1.3查询sortedset中分数(cid)和在(cname)
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        //2判断集合是否空
        //3如果空 从数据库查询 并存入redis
        List<Category> cs = null;
        if (categorys == null || categorys.size() == 0){
            System.out.println("从数据库查询------");
            cs = categoryDao.findAll();
            for (int i=0;i < cs.size();i++){
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else {
            System.out.println("从redis查询------");
            //4不为空 直接redis返回 set数据存入list 统一数据结构
            cs = new ArrayList<>();
            for (Tuple tuple:categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int) tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }
}
