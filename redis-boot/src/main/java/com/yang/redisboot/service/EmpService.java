package com.yang.redisboot.service;

import com.yang.redisboot.entity.Emp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/03/20:49
 */
@Service
public interface EmpService {
    List<Emp> findAll();

    void add(Emp emp);
}
