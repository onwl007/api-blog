package com.onwl007.apiblog.service;

import com.onwl007.apiblog.domain.Comment;
import com.onwl007.apiblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 22:54
 * @desc
 */
@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    /**
     * 查询全部评论
     *
     * @return
     */
    public List<Comment> getCommentAll() {
        return commentRepository.findAll();
    }

    /**
     * 根据id查询评论
     *
     * @param id
     * @return
     */
    public Comment getCommentById(String id) {
        return commentRepository.findCommentById(id);
    }

    /**
     * 查询该用户下的评论
     *
     * @param id
     * @param pageable
     * @return
     */
    public Page<Comment> getCommentByAuthorId(String id, Pageable pageable) {
        return commentRepository.findAllByAuthor_Id(id, pageable);
    }

    /**
     * 根据文章id查询该下面的评论
     *
     * @param id
     * @param pageable
     * @return
     */
    public Page<Comment> getCommentByArticleId(String id, Pageable pageable) {
        return commentRepository.findAllByArticle_IdAndParentIsNull(id, pageable);
    }

    /**
     * 根据关键字模糊查询内容
     *
     * @param keyword
     * @param pageable
     * @return
     */
    public Page<Comment> getCommentByContentLike(String keyword, Pageable pageable) {
        return commentRepository.findCommentByContentLike(keyword, pageable);
    }

    /**
     * 创建或保存评论
     *
     * @param comment
     */
    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 获取该评论的子评论
     *
     * @param id
     * @param pageable
     * @return
     */
    public Page<Comment> getCommentByParent(String id, Pageable pageable) {
        return commentRepository.findAllByParent_Id(id, pageable);
    }

    /**
     * 查询所有留言
     *
     * @param type
     * @param pageable
     * @return
     */
    public Page<Comment> getCommentByType(int type, Pageable pageable) {
        return commentRepository.findAllByType(type, pageable);
    }
}
