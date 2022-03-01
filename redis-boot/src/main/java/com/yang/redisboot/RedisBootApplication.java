package com.yang.redisboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yang.redisboot.dao")
public class RedisBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisBootApplication.class, args);
    }

}
