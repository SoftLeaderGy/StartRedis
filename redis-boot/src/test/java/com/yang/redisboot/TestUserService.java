package com.yang.redisboot;

import com.yang.redisboot.entity.User;
import com.yang.redisboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

@SpringBootTest
class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        List<User> all = userService.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }
}
