package com.yang.redisboot.service;

import com.yang.redisboot.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/01/23:20
 */
@Service
public interface UserService {
    List<User> findAll();
    void deleteById(String id);
}
