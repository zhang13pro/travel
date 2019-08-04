package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: bobobo
 * @Date: 2019/8/3 15:50
 * @Version：1.0
 */
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    //* 根据cid查询总记录数
    public int findTotalCount(int cid) {
        String sql = "select count(*) from tab_route where cid=?";
        return template.queryForObject(sql,Integer.class,cid);
    }

    @Override
    //根据cid  start pageSize 查询当前页的数据集合
    public List<Route> findByPage(int cid, int start, int pageSize) {
        String sql = "select * from tab_route where cid=? limit ?,?";
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),cid,start,pageSize);
    }
}
