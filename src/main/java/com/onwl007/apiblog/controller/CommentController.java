package com.onwl007.apiblog.controller;

import com.onwl007.apiblog.domain.Comment;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.service.CommentService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResultGenerator generator;

    /**
     * 获取全部评论
     *
     * @return
     */
    @GetMapping
    public RestResult listComments() {
        List<Comment> list = commentService.getCommentAll();
        if (list != null && list.size() > 0) {
            return generator.getSuccessResult("评论列表获取成功", list);
        }
        return generator.getFailResult("评论列表获取失败", list);
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
