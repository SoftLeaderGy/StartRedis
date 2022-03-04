package com.yang.redisboot.service.impl;

import com.yang.redisboot.dao.EmpDao;
import com.yang.redisboot.entity.Emp;
import com.yang.redisboot.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/03/20:49
 */
@Service("EmpServiceImpl")
public class EmpServiceImpl implements EmpService {

    @Autowired(required = false)
    private EmpDao empDao;

    @Override
    public List<Emp> findAll() {
        return empDao.findAll();
    }

    @Override
    public void add(Emp emp) {
        empDao.add(emp);
    }
}
