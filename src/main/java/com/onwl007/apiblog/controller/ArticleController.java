package com.onwl007.apiblog.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
import com.onwl007.apiblog.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
            articlePage = articleService.getArticleByTitleLike(1,keyword, pageable);
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
                articlePage = articleService.getArticleByCategoryId(1,category, pageable);
            } else {
                String categoryId = categoryService.getCategortByName(category).getId();
                articlePage = articleService.getArticleByCategoryId(1,categoryId, pageable);
            }

            Pagination pagination = new Pagination(articlePage.getTotalElements(), articlePage.getNumber() + 1, articlePage.getTotalPages(), articlePage.getSize());
            ArticlePagination articlePagination = new ArticlePagination(articlePage.getContent(), pagination);
            return generator.getSuccessResult("查询文章列表成功", articlePagination);
        }

        if (tag == null && category == null && keyword == null) {
            articlePage = articleService.pageArticles(1,pageable);
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
        ArticleMeta meta = article.getMeta();
        int pvs = meta.getPvs() + 1;
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
        List<Article> articles = articleService.listHotArticle(1,sort);
        ArticlePagination articlePagination = new ArticlePagination(articles, null);
        if (articles != null && articles.size() > 0) {
            return generator.getSuccessResult("获取热门文章成功", articlePagination);
        }
        return generator.getFailResult();
    }

    /**
     * 文章归档
     *
     * @return
     * @throws ParseException
     */
    @GetMapping("/archives")
    public RestResult getArticleArchives() throws ParseException {
        List<JsonObject> list = articleService.archivesArticle();
        List<DataArchivesVO> data = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < list.size(); i++) {
            JsonObject jsonObject = list.get(i);
            String year = jsonObject.getAsJsonObject("_id").get("year").getAsString();
            String month = jsonObject.getAsJsonObject("_id").get("month").getAsString();
            JsonArray array = jsonObject.getAsJsonArray("article");
            List<ArticlesVO> articlesVOList = new ArrayList<>();
            for (int j = 0; j < array.size(); j++) {
                String title = array.get(j).getAsJsonObject().get("title").getAsString();
                String _id = array.get(j).getAsJsonObject().get("_id").getAsJsonObject().get("$oid").getAsString();
                String date = array.get(j).getAsJsonObject().get("createAt").getAsJsonObject().get("$date").getAsString().substring(0, 10);
                Long timestamp = Long.parseLong(date) * 1000;
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
                Date createAt = dateFormat.parse(time);
                ArticlesVO articlesVO = new ArticlesVO(_id, title, createAt);
                articlesVOList.add(articlesVO);
            }
            DataArchivesVO dataArchivesVO = new DataArchivesVO(year, month, articlesVOList);
            data.add(dataArchivesVO);
        }

        if (data != null && data.size() > 0) {
            List<ListVO> listVOS = new ArrayList<>();
            int count = 0;
            for (int i = 0; i < data.size(); i++) {
                List<MonthsVO> monthsVOList = new ArrayList<>();
                SimpleDateFormat mFormat = new SimpleDateFormat("MM");
                count += data.get(i).getArticles().size();
                String year = data.get(i).getYear();
                String month = data.get(i).getMonth();
                Date date = mFormat.parse(month);
                mFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
                String monthStr = mFormat.format(date);
                List<ArticlesVO> articlesVOList = data.get(i).getArticles();
                MonthsVO monthsVO = new MonthsVO(month, monthStr, articlesVOList);
                monthsVOList.add(monthsVO);
                ListVO listVO = new ListVO(year, monthsVOList);
                listVOS.add(listVO);
            }

            ArchivesVO archivesVO = new ArchivesVO(count, listVOS);
            return generator.getSuccessResult("获取文章归档成功", archivesVO);
        }
        return generator.getFailResult();
    }

}
