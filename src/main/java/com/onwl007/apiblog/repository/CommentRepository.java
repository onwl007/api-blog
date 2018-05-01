package com.onwl007.apiblog.repository;

import com.onwl007.apiblog.domain.Comment;
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

}
