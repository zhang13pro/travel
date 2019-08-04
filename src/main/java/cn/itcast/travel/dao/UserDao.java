package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @Author: bobobo
 * @Date: 2019/7/30 16:24
 * @Version：1.0
 */
public interface UserDao {
    User findByUsername(String username);

    void save(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);

}
