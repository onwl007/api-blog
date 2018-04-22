package com.onwl007.apiblog.domain;

import com.onwl007.apiblog.vo.CommentMeta;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author onwl007@126.com
 * @date 2018/4/21 14:45
 * @desc 评论
 */
public class Comment {

    @Id
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Boolean getSpam() {
        return spam;
    }

    public void setSpam(Boolean spam) {
        this.spam = spam;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public int getSticky() {
        return sticky;
    }

    public void setSticky(int sticky) {
        this.sticky = sticky;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CommentMeta getMeta() {
        return meta;
    }

    public void setMeta(CommentMeta meta) {
        this.meta = meta;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public Comment getForward() {
        return forward;
    }

    public void setForward(Comment forward) {
        this.forward = forward;
    }
}
