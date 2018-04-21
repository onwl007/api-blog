package com.onwl007.apiblog.domain;

import com.onwl007.apiblog.vo.Extend;
import com.onwl007.apiblog.vo.Github;

import java.util.Date;

/**
 * @author onwl007@126.com
 * @date 2018/4/21 14:47
 * @desc 用户
 */
public class User {
    private String name;
    private String email;
    private String avatar;
    private String site;
    private String slogan;
    private String description;
    // 角色 0 管理员 | 1 普通用户 | 2 github用户，不能更改
    private int role;
    // role = 0的时候才有该项
    private String password;
    // 是否被禁言
    private Boolean mute;
    private String company;
    private String location;
    private Date createAt;
    private Date updateAt;
    // github信息，不能手动更改
    private Github github;
}
