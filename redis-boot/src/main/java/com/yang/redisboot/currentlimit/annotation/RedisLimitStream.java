package com.yang.redisboot.currentlimit.annotation;

import java.lang.annotation.*;

/**
 * @Description: 自定义竹节实现分布式限流
 * @Author: Guo.Yang
 * @Date: 2023/09/22/10:47
 */

@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLimitStream {
    /**
     * 请求限制，一秒内可以允许好多个进入(默认一秒可以支持100个)
     * @return
     */
    int reqLimit() default 100;

    /**
     * 模块名称
     * @return
     */
    String reqName() default "";
}
