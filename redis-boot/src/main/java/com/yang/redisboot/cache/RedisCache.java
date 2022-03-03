package com.yang.redisboot.cache;


import com.yang.redisboot.utils.ApplicationContextUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/02/20:53
 */

/**
 * 1、模仿mybatis缓存的实现自定义RedisCache 实现Cache接口 并实现里边的方法
 * 2、需要一个RedisCache的有参构造方法参数为String类型的id
 * 3、getId()方法 的返回值不可以是null，应该是id给返回去
 */
// 自定义redis缓存实现
public class RedisCache  implements Cache {
//    private static ApplicationContextUtils applicationContextUtils;

    // 当前放入缓存的mapper的namespase
    // com.yang.redisboot.dao.UserDao
    private final String id;

    // 必须存在构造方法
    // 有参构造方法参数为String类型的id
    public RedisCache(String id) {
        System.out.println(id);
        this.id = id;
    }

    //  返回cache的唯一标识
    @Override
    public String getId() {
        return this.id;
    }


    // 缓存存入值
    @Override
    public void putObject(Object key, Object value) {
        System.out.println("key-------" + key.toString());
        System.out.println("value-------" + value);

        // 使用ApplicationContextUtils工具类getBean方法获取工厂，并通过工厂的名字返回bean
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        // 使用redishash类型为存储模型
        redisTemplate.opsForHash().put(id.toString(),key.toString(),value);
    }

    // 缓存获取值
    @Override
    public Object getObject(Object key) {
        System.out.println("key-------" + key.toString());
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate.opsForHash().get(id.toString(), key.toString());
    }

    @Override
    public Object removeObject(Object o) {
        System.out.println("根据指定的key删除缓存");
        return null;
    }

    /**
     * 经过测试 只要sql执行增删改的就会执行 clear方法，来保证数据缓存的一致性
     * 所以 可以在此方法中设置通过id删除缓存数据 id刚好是mapper 的namespase
     */
    @Override
    public void clear() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.delete(id.toString());
//        System.out.println("清空redis缓存");

    }


    // 用来计算缓存数量
    @Override
    public int getSize() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        Long size = redisTemplate.opsForHash().size(id.toString());
        return size.intValue();
    }
}
