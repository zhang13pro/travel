package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * @Author: bobobo
 * @Date: 2019/7/30 16:24
 * @Version：1.0
 *
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    /*
    * 注册用户
    * */
    @Override
    public boolean regist(User user) {
        //      1 根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //          * 2 保存用户信息
        if (u != null) {
            //用户名已存在
            return false;
        }
        //2.2 设置激活码
        user.setCode(UuidUtil.getUuid());
        //2.3 设置状态 N表示未激活
        user.setStatus("N");
        userDao.save(user);
        // 3 激活邮件
        String content = "<a href = 'http://localhost/travel/user/active?code="+user.getCode()+"'>点击激活开始旅行</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");

        return true;
    }

    @Override
    public boolean active(String code) {
        //1 根据激活码查找用户对象
        User user = userDao.findByCode(code);
        if (user != null){
            //2 更新激活码
            userDao.updateStatus(user);
            return true;
        }
        return false;
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
