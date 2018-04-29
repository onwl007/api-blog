package com.onwl007.apiblog.vo;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author onwl007@126.com
 * @date 2018/4/29 20:41
 * @desc 文章归档 根据时间统计
 */
public class ArticlesVO {

    @Id
    private String id;
    //文章标题
    private String title;
    //创建时间
    private Date createdAt;

    public ArticlesVO(String id, String title, Date createdAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
