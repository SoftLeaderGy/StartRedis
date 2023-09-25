package com.yang.redisboot.currentlimit.aspect;

import com.yang.redisboot.currentlimit.annotation.RedisLimitStream;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: MyRedisLimiter注解的切面类
 * @Author: Guo.Yang
 * @Date: 2023/09/22/10:49
 */
@Aspect
@Component
@Slf4j
public class RedisLimiterAspect {
    /**
     * 当前响应请求
     */
    @Autowired
    private HttpServletResponse response;

    /**
     * redis服务
     */
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 执行redis的脚本文件
     */
    @Autowired(required = false)
    private RedisScript<Boolean> rateLimitLua;

    /**
     * 对所有接口进行拦截
     */
    @Pointcut("execution(public * com.yang.redisboot.currentlimit.controller.*.*(..))")
    public void pointcut(){}

    /**
     * 对切点进行继续处理
     */
    @Around("pointcut()")
    public Object process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        //使用反射获取RedisLimitStream注解
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //没有添加限流注解的方法直接放行
        RedisLimitStream redisLimitStream = signature.getMethod().getDeclaredAnnotation(RedisLimitStream.class);
        // 如果接口没有限流注解，直接放行即可
        if(ObjectUtils.isEmpty(redisLimitStream)){
            return proceedingJoinPoint.proceed();
        }
        // 创建一个list，将key放入list，为之后redis执行lua脚本做所需要key的形式
        List<String> keyList = new ArrayList<>();
        keyList.add("ip:" + (System.currentTimeMillis() / 1000));
        // 获取接口上限流注解设置的限流次数
        int value = redisLimitStream.reqLimit();
        // redis执行lua脚本
        /**
         * 参数说明：
         *  1、lua脚本，通过RedisConfiguration中的DefaultRedisScript配置加载进来
         *  2、list形式的redis key
         *  3、redis value
         */
        boolean acquired = (Boolean) redisTemplate.execute(rateLimitLua, keyList, value);
        log.info("执行lua结果：" + acquired);
        // 通过执行结果判断接口是否已经到了接口限制次数
        if(!acquired){
            // 执行接口限流返回
            this.limitStreamBackMsg();
            return null;
        }
        //获取到令牌，继续向下执行
        return proceedingJoinPoint.proceed();
    }

    /**
     * 被拦截的人，提示消息
     */
    private void limitStreamBackMsg() {
        log.info("当前排队人较多，请稍后再试！");
        response.setHeader("Content-Type", "text/html;charset=UTF8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println("{\"code\":503,\"message\":\"当前排队人较多，请稍后再试！\",\"data\":\"null\"}");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}

