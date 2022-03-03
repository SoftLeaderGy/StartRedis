package com.yang.redisboot.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/02/21:20
 */

// 用来获取springboot创建好的工厂
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    // 保留下来的工厂
    private static ApplicationContext applicationContext;

    // 将创建好的工厂传递给这个类
    // Spingboot启动后会创建工厂并执行setApplicationContext方法 将整个工厂传进来并保留传进来的工厂，通过genBean方法获取bean对象
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    // 提供在工厂Utils中获取对象的方法
    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

}
