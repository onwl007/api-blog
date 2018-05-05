package com.onwl007.apiblog.repository;


import com.onwl007.apiblog.domain.Article;
import com.onwl007.apiblog.page.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/22 15:23
 * @desc
 */
public interface ArticleRepository extends MongoRepository<Article, String> {

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    Page<Article> findAll(Pageable pageable);

    /**
     * 根据创建时间降序排列
     *
     * @return
     */
    List<Article> findAllByOrderByCreatedAtDesc();

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
     *
     * @param key
     * @param pageable
     * @return
     */
    Page<Article> findArticleByTitleLike(String key, Pageable pageable);

    /**
     * 查询关联了同一个标签的所有的文章
     *
     * @param id
     * @return
     */
    Page<Article> findAllByTag_Id(String id, Pageable pageable);

    /**
     * 查询关联了同一个分类下的所有文章
     *
     * @param id
     * @param pageable
     * @return
     */
    Page<Article> findAllByCategory_Id(String id, Pageable pageable);

    /**
     * 根据文章标题查询文章
     *
     * @param title
     * @param pageable
     * @return
     */
    Page<Article> findArticleByTitle(String title, Pageable pageable);
}
