package com.onwl007.apiblog.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author onwl007@126.com
 * @date 2018/4/21 14:46
 * @desc 个人动态
 */
public class Moment {

    @Id
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
