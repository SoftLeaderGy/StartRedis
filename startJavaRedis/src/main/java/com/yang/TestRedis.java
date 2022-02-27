package com.yang;

import redis.clients.jedis.Jedis;
import java.util.Set;

/**
 * @Description: 测试java操作redis
 * @Author: Guo.Yang
 * @Date: 2022/02/27/21:20
 */
public class TestRedis {
    public static void main(String[] args) {

        // 创建jedis客户端对象
        Jedis jedis = new Jedis("39.105.228.254",6379);
        // 设置redis密码
        jedis.auth("root123456");

        // 选择使用一个库 默认：使用 0号库
        jedis.select(0);

        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println("key = " + key);
        }

        // 释放资源
        jedis.close();

    }
}
