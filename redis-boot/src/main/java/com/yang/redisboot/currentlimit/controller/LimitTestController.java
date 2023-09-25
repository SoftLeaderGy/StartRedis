package com.yang.redisboot.currentlimit.controller;

import com.yang.redisboot.currentlimit.annotation.RedisLimitStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2023/09/22/10:49
 */
@RestController
@RequestMapping("/limit")
public class LimitTestController {

    /**
     * 压测接口，测试接口限流
     * @return
     */
    @GetMapping("/test")
    @RedisLimitStream(reqName = "测试接口限流", reqLimit = 5)
    public String limitTest(){
        return "success";
    }
}
