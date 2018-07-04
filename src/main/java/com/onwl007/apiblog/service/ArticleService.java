package com.onwl007.apiblog.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.MongoCursor;
import com.onwl007.apiblog.domain.Tag;
import com.onwl007.apiblog.repository.TagRepository;
import org.bson.Document;
import com.onwl007.apiblog.domain.Article;
import com.onwl007.apiblog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/22 16:23
 * @desc
 */
@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    TagRepository tagRepository;

    /**
     * 查询文章
     *
     * @param state    1 前台查询已发布文章 | 0 后台查询所有文章
     * @param pageable
     * @return
     */
    public Page<Article> pageArticles(int state, Pageable pageable) {
        if (state == 1) {
            return articleRepository.findAllByStateEquals(state, pageable);
        }
        return articleRepository.findAll(pageable);
    }

    /**
     * 查询所有文章按照创建时间降序排列
     *
     * @return
     */
    public List<Article> listArticles() {
        return articleRepository.findAllByOrderByCreateAtDesc();
    }

    /**
     * 根据id查询文章
     *
     * @return
     */
    public Article getArticleById(String id) {
        return articleRepository.findArticleById(id);
    }

    /**
     * 对文章标题进行模糊查询
     *
     * @param key
     * @param state    1 前台查询已发布文章 | 0 后台查询所有文章
     * @param pageable
     * @return
     */
    public Page<Article> getArticleByTitleLike(int state, String key, Pageable pageable) {
        if (state == 1) {
            articleRepository.findArticleByTitleLikeAndStateEquals(key, state, pageable);
        }
        return articleRepository.findArticleByTitleLike(key, pageable);
    }

    /**
     * 查询关联同一个标签下的所有文章
     *
     * @param id
     * @param pageable
     * @return
     */
    public Page<Article> getArticleByTagId(String id, Pageable pageable) {
        Tag tag = tagRepository.findTagById(id);
        Tag[] tags = new Tag[1];
        tags[0] = tag;
        return articleRepository.findAllByTagIn(tags, pageable);
    }

    /**
     * 查询关联同一个分类下的所有文章
     *
     * @param id       分类ID
     * @param state    1 前台查询已发布文章 | 0 后台查询所有文章
     * @param pageable
     * @return
     */
    public Page<Article> getArticleByCategoryId(int state, String id, Pageable pageable) {
        if (state == 1) {
            return articleRepository.findAllByCategory_IdAndStateEquals(id, state, pageable);
        }
        return articleRepository.findAllByCategory_Id(id, pageable);
    }

    /**
     * 创建文章或更新文章
     *
     * @param article
     */
    public void createArticle(Article article) {
        articleRepository.save(article);
    }

    /**
     * 删除文章
     */
    public void deleteArticle(String id) {
        articleRepository.deleteById(id);
    }

    /**
     * 根据文章的发布状态查询热门文章
     *
     * @param sort
     * @param state 1 已发布 | 0 未发布
     * @return
     */
    public List<Article> listHotArticle(int state, Sort sort) {
        if (state == 1) {
            return articleRepository.findAllByStateEquals(state, sort);
        }
        return articleRepository.findAll(sort);
    }

    /**
     * mongodb 聚合查询
     * 文章归档
     * 按照时间分组，统计年份，月份都有哪些文章
     *
     * @return
     */
    public List<JsonObject> archivesArticle() {
        List<Document> pipeline = new ArrayList<Document>();
        Document match = new Document("$match", new Document("state", 1));
        pipeline.add(match);
        Document sort = new Document("$sort", new Document("createAt", 1));
        pipeline.add(sort);
        Document project = new Document("$project", new Document("year", new Document("$year", "$createAt"))
                .append("month", new Document("$month", "$createAt"))
                .append("title", 1)
                .append("createAt", 1));
        pipeline.add(project);
        Document group = new Document("$group", new Document("_id", new Document("year", "$year")
                .append("month", "$month"))
                .append("article", new Document("$push", new Document("title", "$title")
                        .append("_id", "$_id")
                        .append("createAt", "$createAt"))));
        pipeline.add(group);
        MongoCursor<Document> cursor = mongoTemplate.getCollection("article").aggregate(pipeline).allowDiskUse(true).iterator();
        List<JsonObject> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Document item = cursor.next();
            String json = item.toJson();
            JsonObject data = new JsonParser().parse(json).getAsJsonObject();
            list.add(data);
        }
        cursor.close();
        return list;
    }

}
