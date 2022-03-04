package com.yang.redisboot;

import com.yang.redisboot.entity.Emp;
import com.yang.redisboot.entity.User;
import com.yang.redisboot.service.EmpService;
import com.yang.redisboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class TestEmpService {

    @Autowired
    private UserService userService;

    @Autowired
    private EmpService empService;
    @Test
    public void test(){
        List<Emp> all = empService.findAll();
        System.out.println("==============");
        List<Emp> all1 = empService.findAll();
    }

    @Test
    public void testAdd(){
        Emp emp = new Emp();
        emp.setId(UUID.randomUUID().toString());
        emp.setName("小郭");
        empService.add(emp);
    }
}
