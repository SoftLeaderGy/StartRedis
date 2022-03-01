package com.yang.redisboot;

import com.yang.redisboot.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class TestBoundAPI {

    // 注入StringRedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * spring data 为了方便我们对redis进行友好的操作，因此给我们提供了bound API 简化操作
     */
    @Test
    public void testbound(){
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        /**
         * 也就是 我们在操作同一个key的时候我们无时无刻都得带着这个key
         */
//        redisTemplate.opsForValue().set("name" , "张三");
//        redisTemplate.opsForValue().append("name","是一个好人");
//        redisTemplate.opsForValue().get("name");

        /**
         * 为了简化操作，我们可以通过bound绑定一个key，并返回一个绑定之后的对象，以后我们对这个对象的操作就是我们对这个key的操作
         */

        // 上边的代码可以简化为：
        BoundValueOperations name = redisTemplate.boundValueOps("name");
        name.set("张三");
        name.append("是个好人");
        String o = (String) name.get();
        System.out.println(o);

        /**
         * 其他数据类型以此类推
         */

        /**
         * 总结：
         * 1、针对于处理key、value 都是string类型使用StringRedisTemplate
         * 2、针对于处理key、value 存在对象 使用RedisTemplate
         * 3、针对于同一个key的多个操作 建议使用boundXXXOps() value、list、Set、Zset的api简化代码
         */
    }
}
