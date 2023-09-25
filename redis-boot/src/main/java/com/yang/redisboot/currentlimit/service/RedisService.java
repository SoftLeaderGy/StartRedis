package com.yang.redisboot.currentlimit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2023/09/22/13:43
 */
@Component
public class RedisService {


    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 执行lua脚本
     * @param redisScript lua源代码脚本
     * @param keyList
     * @param value
     * @return
     */
    public boolean execute(RedisScript<Boolean> redisScript, List<String> keyList, int value) {
        return (Boolean) redisTemplate.execute(redisScript, keyList, String.valueOf(value));
    }


}
