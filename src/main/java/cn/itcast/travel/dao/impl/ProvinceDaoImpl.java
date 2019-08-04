package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.ProvinceDao;
import cn.itcast.travel.domain.Province;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: bobobo
 * @Date: 2019/8/3 10:58
 * @Version：1.0
 */
public class ProvinceDaoImpl implements ProvinceDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        String sql = "select * from redis_ajax";
        return template.query(sql,new BeanPropertyRowMapper<>(Province.class));
    }
}
