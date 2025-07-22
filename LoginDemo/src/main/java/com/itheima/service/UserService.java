package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {

    // 只运行一次
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public User login(String username, String password) {
        SqlSession session = factory.openSession();

        UserMapper userMapper = session.getMapper(UserMapper.class);

        User user = userMapper.select(username, password);

        session.close();

        return user;
    }

    public boolean register(User user) {
        SqlSession session = factory.openSession();

        UserMapper userMapper = session.getMapper(UserMapper.class);

        User u = userMapper.selectByUsername(user.getUsername());
        if(u == null) {
            //用户名不存在，注册
            userMapper.add(user);
            session.commit();
        }
        session.close();
        return u==null;        // true则表示注册成功，否则表示注册失败
    }
}
