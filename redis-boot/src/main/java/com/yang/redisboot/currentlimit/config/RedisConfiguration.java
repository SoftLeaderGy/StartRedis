package com.yang.redisboot.currentlimit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;

/**
 * @Description: 将lua脚本加载到RedisScript中
 * @Author: Guo.Yang
 * @Date: 2023/09/22/10:49
 */
@Configuration
public class RedisConfiguration {

    /**
     * 初始化将lua脚本加载到redis脚本中
     * @return
     */
    @Bean
    public DefaultRedisScript loadRedisScript() {
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("limit.lua"));
        redisScript.setResultType(Boolean.class);
        return redisScript;
    }
}


