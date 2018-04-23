package com.onwl007.apiblog.repository;

import com.onwl007.apiblog.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/22 20:16
 * @desc CategoryRepository
 *
 */
public interface CategoryRepository extends MongoRepository<Category,String> {
    /**
     * 根据list升序排列
     * @return
     */
    List<Category> findAllByOrderByListAsc();

    /**
     * 根据分类id查询
     * @return
     */
    Category findCategoryById(String id);
}
