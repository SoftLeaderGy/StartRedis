package com.yang;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Description: 测试key操作
 * @Author: Guo.Yang
 * @Date: 2022/02/27/21:31
 */
public class TestKeys {
    private Jedis jedis;

    // 创建 TestKeys类之前方法
    @Before
    public void before(){
        // 创建redis对象
        this.jedis = new Jedis("39.105.228.254", 6379);
        this.jedis.auth("root123456");
    }

    // 创建 TestKeys类之后方法
    @After
    public void after(){
        this.jedis.close();
    }


    @Test
    public void testKeys(){
        // 测试redis操作键

        // 删除一个key
//        jedis.del("gy");

        // 删除多个key
//        jedis.del("gy","name");

        // 判断一个key是否存在exits
//        Boolean name = jedis.exists("name");
//        System.out.println(name);

        // 设置一个key超时时间 expire pexpire
        // 设置的key一定是存在的key
//        Long age = jedis.expire("gy", 100);
//        System.out.println(age);

        // 查看一个key超时时间 ttl
        // 查看的一定是存在的key
//        Long age1 = jedis.ttl("gy");
//        System.out.println(age1);

        // 随机获取一个key （不删除）
//        String s = jedis.randomKey();
//        System.out.println(s);

        // 获取key的类型
        String name = jedis.type("name");
        System.out.println(name);

    }
}
