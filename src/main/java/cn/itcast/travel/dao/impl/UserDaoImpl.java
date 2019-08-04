package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: bobobo
 * @Date: 2019/7/30 16:24
 * @Version：1.0
 */
public class UserDaoImpl implements UserDao {
    //获取数据原 连接池对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
//    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ?";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (DataAccessException e) {
//            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code)" +
                "values(?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    /*
    * 根据激活码查询对象
    * */
    @Override
    /*
        Spring JdbcTemplate详解-----读取单个对象
        String sql="select id,name,deptid from user where id=?";
        RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
        User user= jdbcTemplate.queryForObject(sql, rowMapper,52);
    */
    public User findByCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code=?";
            user = template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    /*
    * 修改指定用户激活码状态
    * */
    @Override
    public void updateStatus(User user) {
        String sql = "update tab_user set status='Y' where uid=?";
        template.update(sql,user.getUid());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ? and password=?";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username,password);
        } catch (DataAccessException e) {
//            e.printStackTrace();
        }
        return user;
    }
}
