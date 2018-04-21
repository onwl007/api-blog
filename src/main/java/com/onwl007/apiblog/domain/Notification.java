package com.onwl007.apiblog.domain;

import java.util.Date;

/**
 * @author onwl007@126.com
 * @date 2018/4/21 14:46
 * @desc 通知
 */
public class Notification {
    //通知类型 0 系统通知 | 1 评论通知 | 2 点赞通知 | 3 用户操作通知
    private int type;
    // 类型细化分类
    private Category category;
    // 是否已读
    private Boolean viewed;
    // article user comment 根据情况是否包含
    private Article article;
    private User user;
    private Comment comment;
    // 必填
    private Date createAt;
    private Date updateAt;
}
