package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @Author: bobobo
 * @Date: 2019/7/30 16:23
 * @Version：1.0
 */
public interface UserService {
    boolean regist(User user);

    boolean active(String code);

    User login(User user);
}
