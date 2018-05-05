package com.onwl007.apiblog.controller;

import com.onwl007.apiblog.domain.Comment;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.page.CommentPagination;
import com.onwl007.apiblog.page.Pagination;
import com.onwl007.apiblog.repository.ArticleRepository;
import com.onwl007.apiblog.service.ArticleService;
import com.onwl007.apiblog.service.CommentService;
import com.onwl007.apiblog.service.UserService;
import com.onwl007.apiblog.util.MongoUtil;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/5/1 14:46
 * @desc
 */
@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ResultGenerator generator;

    @Autowired
    private MongoUtil mongoUtil;

    /**
     * 获取全部评论
     *
     * @return
     */
    //@GetMapping
    //public RestResult listComments() {
    //    List<Comment> list = commentService.getCommentAll();
    //    if (list != null && list.size() > 0) {
    //        return generator.getSuccessResult("评论列表获取成功", list);
    //    }
    //    return generator.getFailResult("评论列表获取失败", list);
    //}

    /**
     * 获取全部评论
     *
     * @return
     */
    @GetMapping
    public RestResult listComments(@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                   @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                   @RequestParam(value = "state", required = false) Integer state,
                                   @RequestParam(value = "type", required = false) Integer type,
                                   @RequestParam(value = "author", required = false) String author,
                                   @RequestParam(value = "article", required = false) String article,
                                   @RequestParam(value = "keyword", required = false) String keyword,
                                   @RequestParam(value = "parent", required = false) String parent,
                                   @RequestParam(value = "order", required = false, defaultValue = "-1") String order,
                                   @RequestParam(value = "sortBy", required = false, defaultValue = "createAt") String sortBy) {
        Page<Comment> commentPage = null;
        Sort sort = new Sort(order.equals("-1") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
        Pageable pageable = new PageRequest(page - 1, pageSize, sort);
        if (keyword != null && !keyword.equals("")) {
            commentPage = commentService.getCommentByContentLike(keyword, pageable);
            return generator.getSuccessResult("评论列表获取成功", commentPage);
        }

        if (author != null && !author.equals("")) {
            if (mongoUtil.isValidObjectId(author)) {
                commentPage = commentService.getCommentByAuthorId(author, pageable);
            } else {
                String authorId = userService.getUserByName(author).getId();
                commentPage = commentService.getCommentByAuthorId(authorId, pageable);
            }
            Pagination pagination = new Pagination(commentPage.getTotalElements(), commentPage.getNumber() + 1, commentPage.getTotalPages(), commentPage.getSize());
            CommentPagination commentPagination = new CommentPagination(commentPage.getContent(), pagination);
            return generator.getSuccessResult("评论列表获取成功", commentPagination);
        }

        if (article != null && !article.equals("")) {
            if (mongoUtil.isValidObjectId(article)) {
                commentPage = commentService.getCommentByArticleId(article, pageable);
            } else {
                String articleId = articleRepository.findByTitle(article).getId();
                commentPage = commentService.getCommentByArticleId(articleId, pageable);
            }
            Pagination pagination = new Pagination(commentPage.getTotalElements(), commentPage.getNumber() + 1, commentPage.getTotalPages(), commentPage.getSize());
            CommentPagination commentPagination = new CommentPagination(commentPage.getContent(), pagination);
            return generator.getSuccessResult("评论列表获取成功", commentPagination);
        }

        return generator.getFailResult("评论列表获取失败", commentPage);
    }

    /**
     * 根据id查询评论
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RestResult getCommentItem(@PathVariable("id") String id) {
        Comment comment = commentService.getCommentById(id);
        if (comment != null) {
            return generator.getSuccessResult("获取评论成功", comment);
        }
        return generator.getFailResult("获取评论失败", comment);
    }

    /**
     * 发表评论
     *
     * @param comment
     * @return
     */
    @PostMapping
    public RestResult publishComment(@RequestBody Comment comment) {
        if (comment != null) {
            commentService.createComment(comment);
            return generator.getSuccessResult("发表评论成功", null);
        }
        return generator.getFailResult();
    }

    /**
     * 点赞评论
     *
     * @param like
     * @param id
     * @return
     */
    @PostMapping("/{id}/like")
    public RestResult likeComment(@RequestBody String like, @PathVariable("id") String id) {
        if (like.equals("true")) {
            Comment comment = commentService.getCommentById(id);
            comment.setUps(comment.getUps() + 1);
            commentService.createComment(comment);
            return generator.getSuccessResult("点赞评论成功", null);
        }
        return generator.getFailResult("点赞是吧", null);
    }

}
