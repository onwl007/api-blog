package com.onwl007.apiblog.domain;

import com.onwl007.apiblog.vo.CommentMeta;

import java.util.Date;

/**
 * @author onwl007@126.com
 * @date 2018/4/21 14:45
 * @desc 评论
 */
public class Comment {
    //创建时间
    private Date createAt;
    //修改时间
    private Date updateAt;
    //评论内容
    private String content;
    //markdown渲染后的内容
    private String renderedContent;
    //状态（-2 垃圾评论 | -1 已删除 | 0 待审核 | 1 通过）
    private int state;
    //Akismet判定是否是垃圾评论，方便后台check
    private Boolean spam;
    //用户
    private User author;
    //点赞数
    private int ups;
    //是否置顶（0 否 | 1 是）
    private int sticky;
    //类型（0 文章评论 | 1 站内留言 | 2 其他）
    private int type;
    //评论元数据
    private CommentMeta meta;
    //type为0时此项存在
    private Article article;
    //父评论 parent和forward必须同时存在
    private Comment parent;
    //前一条评论ID，可以是parent的id， 比如 B评论 是 A评论的回复，则B.forward._id = A._id，主要是为了查看评论对话时的评论树构建
    private Comment forward;
}
