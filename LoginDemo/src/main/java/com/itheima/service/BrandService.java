package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {
    // 只运行一次
    static SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Brand> selectAll() {

        SqlSession session = factory.openSession();

        BrandMapper mapper = session.getMapper(BrandMapper.class);

        List<Brand> brands = mapper.selectAll();

        session.close();

        return brands;
    }

    public static void add(Brand brand) {
        SqlSession session = factory.openSession();

        BrandMapper mapper = session.getMapper(BrandMapper.class);

        mapper.add(brand);

        // 增删改都需要提交事务
        session.commit();

        session.close();

    }

    public Brand selectById(int id) {

        SqlSession session = factory.openSession();

        BrandMapper mapper = session.getMapper(BrandMapper.class);

        Brand brand = mapper.selectById(id);

        session.close();

        return brand;

    }

    public void update(Brand brand) {
        SqlSession session = factory.openSession();

        BrandMapper mapper = session.getMapper(BrandMapper.class);

        mapper.update(brand);

        // 增删改都需要提交事务
        session.commit();

        session.close();

    }
}
