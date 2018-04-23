package com.onwl007.apiblog.repository;

import com.onwl007.apiblog.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 22:46
 * @desc
 */
public interface CommentRepository extends MongoRepository<Comment,String> {
}
