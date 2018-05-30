package com.onwl007.apiblog.controller;

import com.onwl007.apiblog.domain.Article;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.page.ArticlePagination;
import com.onwl007.apiblog.page.Pagination;
import com.onwl007.apiblog.service.ArticleService;
import com.onwl007.apiblog.service.CategoryService;
import com.onwl007.apiblog.service.TagService;
import com.onwl007.apiblog.util.CodeMap;
import com.onwl007.apiblog.util.MongoUtil;
import com.onwl007.apiblog.util.ResultGenerator;
import com.onwl007.apiblog.util.ServiceException;
import com.onwl007.apiblog.vo.ArticleMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/26 20:31
 * @desc
 */
@CrossOrigin
@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ResultGenerator generator;

    @Autowired
    private MongoUtil mongoUtil;

    /**
     * 文章列表
     *
     * @return
     */
    @GetMapping
    public RestResult listArticles(@RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "state", required = false) Integer state,
                                   @RequestParam(value = "category", required = false) String category,
                                   @RequestParam(value = "tag", required = false) String tag,
                                   @RequestParam(value = "keyword", required = false) String keyword) {
        Page<Article> articlePage = null;
        Pageable pageable = new PageRequest(page - 1, pageSize);

        if (keyword != null && !keyword.equals("")) {
            articlePage = articleService.getArticleByTitleLike(keyword, pageable);
            Pagination pagination = new Pagination(articlePage.getTotalElements(), articlePage.getNumber() + 1, articlePage.getTotalPages(), articlePage.getSize());
            ArticlePagination articlePagination = new ArticlePagination(articlePage.getContent(), pagination);
            return generator.getSuccessResult("文章类别获取成功", articlePagination);
        }

        if (tag != null && !tag.equals("")) {
            if (mongoUtil.isValidObjectId(tag)) {
                articlePage = articleService.getArticleByTagId(tag, pageable);
            } else {
                String tagId = tagService.getTagByName(tag).getId();
                articlePage = articleService.getArticleByTagId(tagId, pageable);
            }

            Pagination pagination = new Pagination(articlePage.getTotalElements(), articlePage.getNumber() + 1, articlePage.getTotalPages(), articlePage.getSize());
            ArticlePagination articlePagination = new ArticlePagination(articlePage.getContent(), pagination);
            return generator.getSuccessResult("文章列表获取成功", articlePagination);
        }

        if (category != null && !category.equals("")) {
            if (mongoUtil.isValidObjectId(category)) {
                articlePage = articleService.getArticleByCategoryId(category, pageable);
            } else {
                String categoryId = categoryService.getCategortByName(category).getId();
                articlePage = articleService.getArticleByCategoryId(categoryId, pageable);
            }

            Pagination pagination = new Pagination(articlePage.getTotalElements(), articlePage.getNumber() + 1, articlePage.getTotalPages(), articlePage.getSize());
            ArticlePagination articlePagination = new ArticlePagination(articlePage.getContent(), pagination);
            return generator.getSuccessResult("查询文章列表成功", articlePagination);
        }

        if (tag == null && category == null && keyword == null) {
            articlePage = articleService.pageArticles(pageable);
            Pagination pagination = new Pagination(articlePage.getTotalElements(), articlePage.getNumber() + 1, articlePage.getTotalPages(), articlePage.getSize());
            ArticlePagination articlePagination = new ArticlePagination(articlePage.getContent(), pagination);
            return generator.getSuccessResult("文章类别获取成功", articlePagination);
        }

        return generator.getFailResult("文章列表获取失败", articlePage);
    }

    /**
     * 文章详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RestResult getArticleItemById(@PathVariable("id") String id) throws ServiceException {
        if (id.equals("null")) {
            throw new ServiceException("参数错误");
        }
        Article article = articleService.getArticleById(id);
        ArticleMeta meta=article.getMeta();
        int pvs=meta.getPvs()+1;
        meta.setPvs(pvs);
        article.setMeta(meta);
        articleService.createArticle(article);
        if (article != null) {
            return generator.getSuccessResult("文章详情获取成功", article);
        }
        return generator.getCodeMapFailResult(CodeMap.REQUEST_FAIL, article);
    }


    @RequestMapping(value = "/{id}/like", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public RestResult addArticleLike(@RequestBody String like, @PathVariable("id") String id, HttpServletRequest request) {
        String method = request.getMethod();
        if (method.equals("POST")) {
            Article article = articleService.getArticleById(id);
            int ups = article.getMeta().getUps();
            article.getMeta().setUps(ups + 1);
            articleService.createArticle(article);
            return generator.getSuccessResult("文章点赞成功", null);
        } else {
            Article article = articleService.getArticleById(id);
            if (article != null) {
                return generator.getSuccessResult(null);
            }
        }
        return generator.getFailResult();
    }

    /**
     * 查询热门文章
     *
     * @return
     */
    @GetMapping("/hot")
    public RestResult getArticleHot() {
        Sort sort = new Sort(Sort.Direction.DESC, "meta.comments", "meta.ups", "meta.pvs");
        List<Article> articles = articleService.listHotArticle(sort);
        ArticlePagination articlePagination = new ArticlePagination(articles, null);
        if (articles != null && articles.size() > 0) {
            return generator.getSuccessResult("获取热门文章成功", articlePagination);
        }
        return generator.getFailResult();
    }

    @GetMapping("/archives")
    public RestResult getArticleArchives() {
        return generator.getFailResult();
    }

}
