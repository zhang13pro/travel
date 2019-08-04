package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
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
    public int findTotalCount(int cid, String rname) {
//        String sql = "select count(*) from tab_route where cid=?";
        //定义sql模板
        String sql = "select count(*)  from tab_route where 1=1   ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();  //条件
        //判断参数个数
        if (cid != 0){
            sb.append(" and cid=? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sql = sb.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    //根据cid  start pageSize 查询当前页的数据集合
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
//        String sql = "select * from tab_route where cid=? limit ?,?";
        String sql = "select * from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();  //条件
        //判断参数个数
        if (cid != 0){
            sb.append(" and cid=? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ?,? ");
        sql = sb.toString();
        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),params.toArray());
    }
}
