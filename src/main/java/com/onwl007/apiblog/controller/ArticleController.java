package com.onwl007.apiblog.controller;

import com.onwl007.apiblog.domain.Article;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.service.ArticleService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/26 20:31
 * @desc
 */
@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ResultGenerator generator;

    @GetMapping
    public RestResult listArticles(){
        List<Article> articles=articleService.listArticles();
        if (articles!=null&&articles.size()>0){
            return generator.getSuccessResult("获取文章列表成功",articles);
        }
        return generator.getFailResult();
    }

}
