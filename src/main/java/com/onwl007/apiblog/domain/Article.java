package com.onwl007.apiblog.domain;

import com.onwl007.apiblog.vo.ArticleMeta;

import java.util.Date;

/**
 * @author onwl007@126.com
 * @date 2018/4/21 14:20
 * @desc 文章实体
 */
public class Article {
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
    private Category category;
    //标签
    private Tag tag;
    //缩略图
    private String thumb;
    //文章状态 (0 草稿 | 1 已发布)
    private int state;
    //永久链接
    private String permalink;
    //创建日期
    private Date createdAt;
    //更新日期
    private Date updateAt;
    //发布日期
    private Date publishAt;
    //文章元数据 （浏览量，喜欢数，评论数）
    private ArticleMeta meta;
}
