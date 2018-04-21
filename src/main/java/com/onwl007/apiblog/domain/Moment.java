package com.onwl007.apiblog.domain;

import java.util.Date;

/**
 * @author onwl007@126.com
 * @date 2018/4/21 14:46
 * @desc 个人动态
 */
public class Moment {
    //内容
    private String content;
    //位置
    private Object location;
    //状态（0 未发布 | 1 已发布）
    private int state;
    //创建日期
    private Date createAt;
    //修改日期
    private Date updateAt;
}
