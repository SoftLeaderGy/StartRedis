package com.yang.redisboot;

import com.yang.redisboot.entity.User;
import com.yang.redisboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.List;

@SpringBootTest
class TestMD5 {

    @Test
    public void test(){
        // 利用spring框架提供的MD5工具类
        String key = "-1544147975:587974899:com.yang.redisboot.dao.EmpDao.findAll:0:2147483647:select * from t_emp:SqlSessionFactoryBean";

        // DigestUtils 工具类的 md5DigestAsHex方法 会将 任何，文件字符，加密成 32位 16进制的服务串
        String s = DigestUtils.md5DigestAsHex(key.getBytes());

        System.out.println(s); // 9ac8f99013a99b8f634a649dd1ed91bb
    }
}
