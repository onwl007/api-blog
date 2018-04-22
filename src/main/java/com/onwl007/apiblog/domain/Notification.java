package com.onwl007.apiblog.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author onwl007@126.com
 * @date 2018/4/21 14:46
 * @desc 通知
 */
public class Notification {

    @Id
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
