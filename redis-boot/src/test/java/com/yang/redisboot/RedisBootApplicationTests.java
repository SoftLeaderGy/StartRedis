package com.yang.redisboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

        // 设置一个键值 且超时时间为120秒 四个参数： 1key 2value 3超时时间 4超时时间的之间类型
        stringRedisTemplate.opsForValue().set("code","2312",120, TimeUnit.SECONDS);

        //追加在一个key的value后边的值
        Integer append = stringRedisTemplate.opsForValue().append("name", "他是一个好人");

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

//        Boolean age = stringRedisTemplate.expire("age", Duration.ofDays(100)); // 设置key的超时时间
        Long age1 = stringRedisTemplate.getExpire("age");
        System.out.println(age1);

//        stringRedisTemplate.rename("age","age1"); // 修改key的名字，如果没有key会报错

        // 理论上是不抛异常的，可能api有些变化
//        Boolean aBoolean = stringRedisTemplate.renameIfAbsent("qwe", "qww"); // 修改key的名字，但修改之前会进行判断是否有这个key

        Boolean age2 = stringRedisTemplate.move("age", 1);// 移动一个可以 到1号库


    }

    @Test
    public void testList(){
        Long aLong = stringRedisTemplate.opsForList().leftPush("names", "小张");

        Long aLong1 = stringRedisTemplate.opsForList().rightPush("testList", "test");

        Long aLong2 = stringRedisTemplate.opsForList().leftPushAll("testLeftPushAll", "v1", "v2");


        ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.add("666");
        stringRedisTemplate.opsForList().leftPushAll("testPushAllOfList",list);

        List<String> testPushAllOfList = stringRedisTemplate.opsForList().range("testPushAllOfList",0,-1);
        for (String s : testPushAllOfList) {
            System.out.println("testPushAllOfList --- >" + s);
        }

    }

    @Test
    public void testSet(){
        // 设置一个set集合
         stringRedisTemplate.opsForSet().add("testSet","张三","张三","李四");

         // 查看set集合成员
        Set<String> testSet = stringRedisTemplate.opsForSet().members("testSet");
        for (String s : testSet) {
            System.out.println("testSet" + s);
        }

        // 查看testSet的值的长度
        Long testSet1 = stringRedisTemplate.opsForSet().size("testSet");
        System.out.println(testSet1);
    }

    @Test
    public void testZset(){
        // 创建一个zset集合
        Boolean add = stringRedisTemplate.opsForZSet().add("zset", "小黑", 10);

        // 便利zset集合
        Set<String> zset = stringRedisTemplate.opsForZSet().range("zset", 0, -1);
        for (String s : zset) {
            System.out.println(s);
        }
        System.out.println("-----------");
        // 获取 指定下标范围的zset元素
        Set<ZSetOperations.TypedTuple<String>> zset1 = stringRedisTemplate.opsForZSet().rangeByScoreWithScores("zset", 0, 100);

        for (ZSetOperations.TypedTuple<String> stringTypedTuple : zset1) {
            System.out.println(stringTypedTuple.getValue());// 元素
            System.out.println(stringTypedTuple.getScore());// 分数
        }
    }

    @Test
    public void testHash(){
        // Hash类型 和java里的haspmap很像
        // 3个参数 redis里边的大key 大key里边的 key value
        stringRedisTemplate.opsForHash().put("maps","name","张三");

        // 获取大key里边的多个key的值
        List<Object> maps = stringRedisTemplate.opsForHash().multiGet("maps", Arrays.asList("name","age"));

        // 获取hash类型key 的key的value
        Object o = stringRedisTemplate.opsForHash().get("maps", "name");

        //// 获取hash类型key 的value
        List<Object> maps1 = stringRedisTemplate.opsForHash().values("maps");

        // 获取hash类型key 的key
        Set<Object> maps2 = stringRedisTemplate.opsForHash().keys("maps");


    }
}
