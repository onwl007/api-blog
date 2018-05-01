package com.onwl007.apiblog.service;

import com.onwl007.apiblog.domain.Category;
import com.onwl007.apiblog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/22 20:42
 * @desc Service
 */
@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * 查询所有分类并根据list升序排列
     * @return
     */
    public List<Category> listCategory(){
        return categoryRepository.findAllByOrderByListAsc();
    }

    /**
     * 根据分类id查询
     * @param id
     * @return
     */
    public Category getCategoryById(String id){
        return categoryRepository.findCategoryById(id);
    }

    /**
     * 创建或者更新分类
     * @param category
     */
    public void createCategory(Category category){
        categoryRepository.save(category);
    }

    /**
     * 根据分类id删除
     * @param id
     */
    public void deleteCategory(String id){
        categoryRepository.deleteById(id);
    }
}
