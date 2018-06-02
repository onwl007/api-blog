package com.onwl007.apiblog.domain;

import com.onwl007.apiblog.mongodbconfig.CascadeSave;
import com.onwl007.apiblog.vo.ArticleMeta;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

/**
 * @author onwl007@126.com
 * @date 2018/4/21 14:20
 * @desc 文章实体
 */
public class Article {

    @Id
    private String id;
    //文章标题
    private String title;
    //文章关键字 (FOR SEO)
    private String keywords;
    //文章摘要 (FOR SEO)
    private String description;
    //文章原始 Markdown 内容
    private String content;
    //Markdown 渲染后的 HTML 内容
    private String renderedContent;
    //分类
    @DBRef
    @CascadeSave
    private Category category;
    //标签
    @DBRef
    @CascadeSave
    private Tag tag;
    //缩略图
    private String thumb;
    //文章状态 (0 草稿 | 1 已发布)
    private int state;
    //永久链接
    private String permalink;
    //创建日期
    private Date createAt;
    //更新日期
    private Date updateAt;
    //发布日期
    private Date publishAt;
    //文章元数据 （浏览量，喜欢数，评论数）
    private ArticleMeta meta;

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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRenderedContent() {
        return renderedContent;
    }

    public void setRenderedContent(String renderedContent) {
        this.renderedContent = renderedContent;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createdAt) {
        this.createAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(Date publishAt) {
        this.publishAt = publishAt;
    }

    public ArticleMeta getMeta() {
        return meta;
    }

    public void setMeta(ArticleMeta meta) {
        this.meta = meta;
    }
}
