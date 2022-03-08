package com.yang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/07/20:32
 */
@Configuration
@EnableRedisHttpSession // 将整个应用中使用session的数据全部交给redis管理
public class RedisSessionManage {
}
