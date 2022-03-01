package com.yang.redisboot.dao;

import com.yang.redisboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/01/23:10
 */
public interface UserDao {
    List<User> findAll();
}
