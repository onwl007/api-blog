package com.onwl007.apiblog.repository;

import com.onwl007.apiblog.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 22:46
 * @desc
 */
public interface CommentRepository extends MongoRepository<Comment, String> {

    /**
     * 根据id查询评论
     *
     * @param id
     * @return
     */
    Comment findCommentById(String id);

    /**
     * 根据评论内容模糊查询
     *
     * @param keyword
     * @param pageable
     * @return
     */
    Page<Comment> findCommentByContentLike(String keyword, Pageable pageable);

    /**
     * 根据用户的id查询其评论
     *
     * @param id
     * @param pageable
     * @return
     */
    Page<Comment> findAllByAuthor_Id(String id, Pageable pageable);

    /**
     * 根据文章id查询该下面的评论
     *
     * @param id
     * @param pageable
     * @return
     */
    Page<Comment> findAllByArticle_IdAndParentIsNull(String id, Pageable pageable);

    /**
     * 查询子评论
     *
     * @param id
     * @param pageable
     * @return
     */
    Page<Comment> findAllByParent_Id(String id, Pageable pageable);
}
