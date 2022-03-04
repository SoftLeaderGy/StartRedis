package com.yang.redisboot.dao;

import com.yang.redisboot.entity.Emp;

import java.util.List;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/03/20:53
 */
public interface EmpDao {
    List<Emp> findAll();

    void add(Emp emp);
}
