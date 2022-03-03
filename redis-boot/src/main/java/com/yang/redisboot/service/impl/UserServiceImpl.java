package com.yang.redisboot.service.impl;

import com.yang.redisboot.dao.UserDao;
import com.yang.redisboot.entity.User;
import com.yang.redisboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/01/23:21
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void deleteById(String id) {
        userDao.deleteById(id);
    }
}
