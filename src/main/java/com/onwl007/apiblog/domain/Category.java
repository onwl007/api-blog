package com.onwl007.apiblog.domain;

import com.onwl007.apiblog.mongodbconfig.CascadeSave;
import com.onwl007.apiblog.vo.Extend;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

/**
 * @author onwl007@126.com
 * @date 2018/4/21 14:45
 * @desc 分类
 */
public class Category {

    @Id
    private String id;
    private String name;
    private String description;
    private Date createAt;
    private Date updateAt;
    private int list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getList() {
        return list;
    }

    public void setList(int list) {
        this.list = list;
    }

}
