package com.onwl007.apiblog.repository;

import com.onwl007.apiblog.domain.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 21:36
 * @desc
 */
public interface TagRepository extends MongoRepository<Tag, String> {

    /**
     * 根据创建时间降序排列
     *
     * @return
     */
    List<Tag> findAllByOrderByCreateAtDesc();

    /**
     * 根据标签id查询
     *
     * @param id
     * @return
     */
    Tag findTagById(String id);

    /**
     * 根据标签的名称查询
     *
     * @param name
     * @return
     */
    Tag findTagByName(String name);
}
