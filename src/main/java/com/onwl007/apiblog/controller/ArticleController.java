package com.onwl007.apiblog.controller;

import com.onwl007.apiblog.domain.Article;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.service.ArticleService;
import com.onwl007.apiblog.util.CodeMap;
import com.onwl007.apiblog.util.ResultGenerator;
import com.onwl007.apiblog.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * 文章列表
     * @return
     */
    @GetMapping
    public RestResult listArticles(){
        List<Article> articles=articleService.listArticles();
        if (articles!=null&&articles.size()>0){
            return generator.getSuccessResult("获取文章列表成功",articles);
        }
        return generator.getFailResult();
    }

    /**
     * 文章详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RestResult getArticleItemById(@PathVariable("id") String id) throws ServiceException{
        if (id.equals("null")){
            throw new ServiceException("参数错误");
        }
        Article article=articleService.getArticleById(id);
        if (article!=null){
            return generator.getSuccessResult("文章详情获取成功",article);
        }
        return generator.getCodeMapFailResult(CodeMap.REQUEST_FAIL,article);
    }

    //@GetMapping("/{id}/like")
    //public RestResult addArticleLike(){}

}
