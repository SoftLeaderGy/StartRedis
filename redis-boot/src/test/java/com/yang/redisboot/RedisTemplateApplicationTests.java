package com.yang.redisboot;

import com.yang.redisboot.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisTemplateApplicationTests {

    // 注入StringRedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate(){


        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("小陈");
        user.setAge(18);
        user.setBir(new Date());

        /**
         * 使用 redisTemplate去取StringRedisTemplate存进去的key时，是取不出来的
         * 因为 redisTemplate 无论存值、取值 都是要序列化完成之后存进去、或者按序列化的key取出来后在反序列化出来
         */
//        Object names = redisTemplate.opsForValue().get("names");
//        System.out.println(names); // 输出：null

        /**
         * 存进去的以user为key的User对象
         * 注意：如果使用终端命令取的话  get user 是取不出来的，因为看似java程序我们存进去的是以user为key
         * 但是实际是user序列化以后的值才是我们存进去的key
         * 所以我们想要存储的对象实体一定要实现序列化接口 Serializable
         */

        // 存入对象
//        User user = new User();
//        user.setId(UUID.randomUUID().toString());
//        user.setName("小陈");
//        user.setAge(18);
//        user.setBir(new Date());
//        redisTemplate.opsForValue().set("user",user);

        // 获取对象
//        User user = (User) redisTemplate.opsForValue().get("user");
//        System.out.println(user); //输出：User(id=b312bd22-6053-4f13-a4e1-ae2dedc932fb, age=18, bir=Tue Mar 01 20:28:54 CST 2022, name=小陈)

        /**
         * 存进去的key、value都是序列化的话 这样对于我们是有局限性的
         * 我们想要的是存进去的key是String 对象是序列化的
         * 我们可以 修改我们的redisTemplate的序列化方式
         * 默认的我们的redisTemplate对象的使用的是jbk的序列化策略
         */

        // 可以打印出redisTemplate key的序列化方案
        RedisSerializer keySerializer = redisTemplate.getKeySerializer();
        System.out.println(keySerializer);

        /**
         * 如果我们想修改key不序列化的话，也就是存就去的是个字符串，用字符串去取可以取出来的话
         * 我们可以修改redisTemplate key的序列化策略
         */

        // 修改redisTemplate key的序列化策略
        redisTemplate.setKeySerializer(new StringRedisSerializer());

//        User user = new User();
//        user.setId(UUID.randomUUID().toString());
//        user.setName("小陈");
//        user.setAge(18);
//        user.setBir(new Date());
//        redisTemplate.opsForValue().set("user",user);

//        redisTemplate.opsForList().leftPush("list",user);
//
//        redisTemplate.opsForSet().add("set",user);
//
//        redisTemplate.opsForZSet().add("zset",user,100);



        redisTemplate.opsForHash().put("map","user",user);

        /**
         * 发现问题
         * redisTemplate.opsForHash().put("map","user",user);
         * 设置的redis的key是没有被序列化的，但是hash里边的key是被序列化的，这也不是我们想要的
         * 于是我们可以修改redis.hash里边key的序列化策略
         */
        // 设置redis hash；里边key的序列化策略
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        // 存入redis key 和hash key均未必序列化对象
        redisTemplate.opsForHash().put("mapOne","user",user);
    }
}
