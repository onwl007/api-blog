package com.onwl007.apiblog.controller.admin;

import com.onwl007.apiblog.domain.Article;
import com.onwl007.apiblog.domain.Category;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.page.ArticlePagination;
import com.onwl007.apiblog.page.Pagination;
import com.onwl007.apiblog.service.ArticleService;
import com.onwl007.apiblog.service.CategoryService;
import com.onwl007.apiblog.service.TagService;
import com.onwl007.apiblog.util.MongoUtil;
import com.onwl007.apiblog.util.ResultGenerator;
import com.onwl007.apiblog.util.ServiceException;
import com.onwl007.apiblog.vo.ArticleMeta;
import com.youbenzi.mdtool.tool.MDTool;
import org.markdownj.MarkdownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author onwl007@126.com
 * @date 2018/6/10 15:57
 * @desc
 */
@RestController
@CrossOrigin
@RequestMapping("backend")
public class AdminArticleController {

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

    @GetMapping("articles")
    public RestResult listArticles(@RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "state", required = false) Integer state,
                                   @RequestParam(value = "category", required = false) String category,
                                   @RequestParam(value = "tag", required = false) String tag,
                                   @RequestParam(value = "keyword", required = false) String keyword,
                                   @RequestParam(value = "isTrusted", required = false) String isTrusted) {
        Page<Article> articlePage = null;
        Pageable pageable = new PageRequest(page - 1, pageSize);

        if (keyword != null && !keyword.equals("")) {
            articlePage = articleService.getArticleByTitleLike(0, keyword, pageable);
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
                articlePage = articleService.getArticleByCategoryId(0, category, pageable);
            } else {
                String categoryId = categoryService.getCategortByName(category).getId();
                articlePage = articleService.getArticleByCategoryId(0, categoryId, pageable);
            }

            Pagination pagination = new Pagination(articlePage.getTotalElements(), articlePage.getNumber() + 1, articlePage.getTotalPages(), articlePage.getSize());
            ArticlePagination articlePagination = new ArticlePagination(articlePage.getContent(), pagination);
            return generator.getSuccessResult("查询文章列表成功", articlePagination);
        }

        articlePage = articleService.pageArticles(0, pageable);
        Pagination pagination = new Pagination(articlePage.getTotalElements(), articlePage.getNumber() + 1, articlePage.getTotalPages(), articlePage.getSize());
        ArticlePagination articlePagination = new ArticlePagination(articlePage.getContent(), pagination);
        return generator.getSuccessResult("文章类别获取成功", articlePagination);
    }

    /**
     * 修改文章
     *
     * @param id
     * @param article
     * @return
     */
    @PatchMapping("/articles/{id}")
    public RestResult modifyArticle(@PathVariable("id") String id, @RequestBody Article article) {
        MarkdownProcessor markdownProcessor = new MarkdownProcessor();
        String html = markdownProcessor.markdown(article.getContent());
        Article blog = articleService.getArticleById(id);
        if (article.getTitle() == null) {
            blog.setState(article.getState());
            articleService.createArticle(blog);
            return generator.getSuccessResult("文章发布成功", article);
        }
        if (article.getTitle() != null) {
            Category category = article.getCategory();
            blog.setCategory(category);
            blog.setKeywords(article.getKeywords());
            blog.setState(article.getState());
            blog.setMeta(article.getMeta());
            blog.setUpdateAt(article.getUpdateAt());
            blog.setTitle(article.getTitle());
            blog.setThumb(article.getThumb());
            blog.setRenderedContent(article.getRenderedContent());
            blog.setPublishAt(article.getPublishAt());
            blog.setPermalink(article.getPermalink());
            blog.setDescription(article.getDescription());
            blog.setContent(article.getContent());
            blog.setTag(article.getTag());
            blog.setRenderedContent(html);
            articleService.createArticle(blog);
            return generator.getSuccessResult("文章更新成功", article);
        }
        return generator.getFailResult();
    }

    /**
     * 创建文章
     *
     * @param article
     * @return 创建文章时报400的错误是因为分类和标签都是和文章关联了，分类已经创建，写文章时选择分类，然后保存时，
     * 因为这个分类已经存在，程序又想着去创建，所以保存新建的文章失败，选择标签时也时因为这样。
     */
    @PostMapping("/articles")
    public RestResult publishArticle(@RequestBody Article article) {
//        MarkdownProcessor markdownProcessor = new MarkdownProcessor();
//        String html = markdownProcessor.markdown(article.getContent());
        String html=MDTool.markdown2Html(article.getContent());
        if (article != null) {
            ArticleMeta meta = new ArticleMeta(0, 0, 0);
            article.setMeta(meta);
            article.setRenderedContent(html);
            article.setUpdateAt(new Date());
            article.setPublishAt(new Date());
            articleService.createArticle(article);
            return generator.getSuccessResult("文章创建成功", article);
        }
        return generator.getFailResult();
    }

    /**
     * 获取文章
     *
     * @param id
     * @return
     */
    @GetMapping("/articles/{id}")
    public RestResult getArticleItemById(@PathVariable("id") String id) {
        Article article = articleService.getArticleById(id);
        if (article != null) {
            return generator.getSuccessResult("文章详情获取成功", article);
        }
        return generator.getFailResult();
    }

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @DeleteMapping("/articles/{id}")
    public RestResult deleteArticle(@PathVariable String id) {
        if (id != null && !id.equals("")) {
            articleService.deleteArticle(id);
            return generator.getSuccessResult("删除文章成功");
        }
        return generator.getFailResult("删除文章失败", null);
    }
}
