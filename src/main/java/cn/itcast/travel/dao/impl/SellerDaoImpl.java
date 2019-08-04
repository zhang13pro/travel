package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: bobobo
 * @Date: 2019/8/4 19:13
 * @Version：1.0
 */
public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller findBySid(int sid) {
        String sql = "select * from tab_seller where sid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Seller.class),sid);
    }
}
