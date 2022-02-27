package com.yang;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Description: 测试List操作
 * @Author: Guo.Yang
 * @Date: 2022/02/27/21:31
 */
public class TestList {
    private Jedis jedis;

    // 创建 TestList类之前方法
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
    public void testList(){

        // lpush
        jedis.lpush("name1","张三","王武","李四");

        // rpush
        jedis.rpush("name1","xaioming");

        // lrange
        List<String> name1 = jedis.lrange("name1", 0, -1);
        for (String s : name1) {
            System.out.println(s);
        }

        // lpop
        String name11 = jedis.lpop("name1");
        System.out.println(name11);

        // rpop
        String name12 = jedis.rpop("name1");
        System.out.println(name12);

        // linsert
        jedis.linsert("lists", BinaryClient.LIST_POSITION.BEFORE, "小黑", "小陈");
    }
}
