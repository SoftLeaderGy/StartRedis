package com.yang;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Description: 测试string操作
 * @Author: Guo.Yang
 * @Date: 2022/02/27/21:31
 */
public class TestString {
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
        // set
        String set = jedis.set("name", "小陈");

        // get
        String name = jedis.get("name");
        System.out.println(name);

        //mset
        jedis.mset("content", "好人", "address", "海淀区");

        //mget
        List<String> mget = jedis.mget("content", "address");
        for (String s : mget) {
            System.out.println(s);
        }

        //getset
        String set1 = jedis.getSet("name", "小明");
        System.out.println(set1);
    }
}
