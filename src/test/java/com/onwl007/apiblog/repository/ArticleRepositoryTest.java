package com.onwl007.apiblog.repository;

import com.google.gson.Gson;
import com.onwl007.apiblog.ApiBlogApplication;
import com.onwl007.apiblog.domain.Article;
import com.onwl007.apiblog.vo.ArticleMeta;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/22 17:01
 * @desc 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiBlogApplication.class)
public class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    public void testSave(){
        Article article=new Article();
        ArticleMeta meta=new ArticleMeta();
        article.setTitle("我最帅");
        article.setContent("王先森007是最帅的，宇宙第一帅");
        article.setDescription("好帅");
        article.setKeywords("帅");
        article.setCreatedAt(new Date());
        meta.setComments(666);
        meta.setPvs(88888);
        meta.setUps(520);
        article.setMeta(meta);
        articleRepository.save(article);
        Article shuai=articleRepository.findByTitle("我最帅");
        Gson gson=new Gson();
        System.out.println(gson.toJson(shuai));
    }

    @Test
    public void testFindAllOrderByCreateAtDesc(){
        Gson gson=new Gson();
        List<Article> articles=articleRepository.findAllByOrderByCreatedAtDesc();
        System.out.println(gson.toJson(articles));
    }

    @Test
    public void testDeleteById(){
        Article shuai=articleRepository.findByTitle("我最帅");
        articleRepository.deleteById(shuai.getId());
    }

    @Test
    public void testFindById(){
        Article shuai=articleRepository.findArticleById("5adc738fe884912f388de507");
        Gson gson=new Gson();
        System.out.println(gson.toJson(shuai));
    }
}
