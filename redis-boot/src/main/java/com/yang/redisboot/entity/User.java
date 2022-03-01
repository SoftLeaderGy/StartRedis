package com.yang.redisboot.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/01/20:17
 */
@Data
public class User implements Serializable {

    private String id;
    private Integer age;
    private Date bir;
    private String name;
}
