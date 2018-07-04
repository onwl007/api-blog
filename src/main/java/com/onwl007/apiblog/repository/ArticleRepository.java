package com.onwl007.apiblog.repository;


import com.onwl007.apiblog.domain.Article;
import com.onwl007.apiblog.domain.Tag;
import com.onwl007.apiblog.page.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/22 15:23
 * @desc
 */
public interface ArticleRepository extends MongoRepository<Article, String> {

    /**
     * 后台查询所有文章
     *
     * @param pageable
     * @return
     */
    Page<Article> findAll(Pageable pageable);

    /**
     * 前台查询已发布的文章
     *
     * @param state    1 已发布 | 0 未发布
     * @param pageable
     * @return
     */
    Page<Article> findAllByStateEquals(int state, Pageable pageable);

    /**
     * 根据创建时间降序排列
     *
     * @return
     */
    List<Article> findAllByOrderByCreateAtDesc();

    /**
     * 根据文章的发布状态查询热门文章
     *
     * @param state 1 已发布 | 0 未发布
     * @param sort  排序
     * @return
     */
    List<Article> findAllByStateEquals(int state, Sort sort);

    /**
     * 根据文章id查询为文章
     *
     * @return
     */
    Article findArticleById(String id);

    /**
     * 根据文章标题查询
     *
     * @param title
     * @return
     */
    Article findByTitle(String title);

    /**
     * 对文章标题进行模糊查询
     * 查询所有文章
     *
     * @param key
     * @param pageable
     * @return
     */
    Page<Article> findArticleByTitleLike(String key, Pageable pageable);

    /**
     * 对文章标题进行模糊查询
     * 查询已发布文章
     *
     * @param key      标题
     * @param state    1 已发布 | 0 未发布
     * @param pageable
     * @return
     */
    Page<Article> findArticleByTitleLikeAndStateEquals(String key, int state, Pageable pageable);

    /**
     * 查询关联了同一个标签的所有文章
     *
     * @param tag
     * @param pageable
     * @return
     */
    Page<Article> findAllByTagIn(Tag[] tag, Pageable pageable);

    /**
     * 后台查询关联了同一个分类下的所有文章
     *
     * @param id
     * @param pageable
     * @return
     */
    Page<Article> findAllByCategory_Id(String id, Pageable pageable);

    /**
     * 前台查询关联了同一个分类下的已发布文章
     *
     * @param id       分类ID
     * @param state    1 已发布 | 0 未发布
     * @param pageable
     * @return
     */
    Page<Article> findAllByCategory_IdAndStateEquals(String id, int state, Pageable pageable);

    /**
     * 根据文章标题查询文章
     *
     * @param title
     * @param pageable
     * @return
     */
    Page<Article> findArticleByTitle(String title, Pageable pageable);
}
