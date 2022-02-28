package com.yang.redisboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;

@SpringBootTest
class RedisBootApplicationTests {

    // 注入StringRedisTemplate
    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    // 操作redis中字符串 opsForValue 实际上操作的就是redis中的String类型
    // 理解： stringRedisTemplate.opsFor谁 操作的就是谁，opsForList  就是操作的redis中的list类型
    @Test
    public void testString(){
        stringRedisTemplate.opsForValue().set("name","小陈");

        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }


    @Test
    public void testKey(){
        Boolean name = stringRedisTemplate.delete("qq"); // 删除一个key
        System.out.println(name);
        Boolean name1 = stringRedisTemplate.hasKey("qw"); // 判断key是否存在
        System.out.println(name1);
        DataType name2 = stringRedisTemplate.type("666"); // 判断key的类型
        System.out.println(name2);
        Set<String> keys = stringRedisTemplate.keys("*"); // 获取redis中的所有key
        System.out.println(keys);
    }

}
