package com.onwl007.apiblog.repository;

import com.google.gson.Gson;
import com.onwl007.apiblog.ApiBlogApplication;
import com.onwl007.apiblog.domain.Article;
import com.onwl007.apiblog.domain.Category;
import com.onwl007.apiblog.domain.Tag;
import com.onwl007.apiblog.vo.ArticleMeta;
import javafx.scene.control.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TagRepository tagRepository;

    @Test
    public void testSave(){
        Article article=new Article();
        ArticleMeta meta=new ArticleMeta();
        Category category=new Category();
        Tag tag=new Tag();
        article.setTitle("我最帅123123456765756756");
        article.setContent("王先森007是最帅的，宇宙第一帅");
        article.setDescription("好帅");
        article.setKeywords("真帅");
        article.setCreateAt(new Date());
        //category.setName("code");
        //category.setCreateAt(new Date());
        //category.setDescription("代码");
        category=categoryRepository.findCategoryById("5ae8812ce884912d2002d37a");
        //categoryRepository.save(category);
        //tag.setCreateAt(new Date());
        //tag.setName("思想");
        //tag.setDescription("美好思想");
        tag=tagRepository.findTagById("5ae88286e8849133783c3145");
        //tagRepository.save(tag);
        meta.setComments(666);
        meta.setPvs(88888);
        meta.setUps(520);
        article.setMeta(meta);
        article.setCategory(category);
        article.setTag(tag);
        articleRepository.save(article);
        Article shuai=articleRepository.findByTitle("我最帅");
        Gson gson=new Gson();
        System.out.println(gson.toJson(shuai));
    }

    @Test
    public void testFindArticleByTitleLike(){
        Gson gson=new Gson();
        Pageable pageable=new PageRequest(0,10);
        Page<Article> page =articleRepository.findArticleByTitleLike("123",pageable);
        System.out.println(gson.toJson(page));
    }

    @Test
    public void testFindArticleByCategoryId(){
        Gson gson=new Gson();
        Pageable pageable=new PageRequest(0,10);
        Page<Article> page=articleRepository.findAllByCategory_Id("5ae8812ce884912d2002d37a",pageable);
        System.out.println(page);
    }

    @Test
    public void testFindAllOrderByCreateAtDesc(){
        Gson gson=new Gson();
        List<Article> articles=articleRepository.findAllByOrderByCreateAtDesc();
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
