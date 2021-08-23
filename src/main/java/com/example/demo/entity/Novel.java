package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wan
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021年08月06日 10:07:48
 */
@Data
@NoArgsConstructor
public class Novel {
    private String novelName;
    private String author;
    private String path;
    private Date createTime;
    private Date updateTime;
}
