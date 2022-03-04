package com.yang.redisboot.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/03/20:50
 */
@Data
public class Emp implements Serializable {
    private String id;
    private String name;
}
