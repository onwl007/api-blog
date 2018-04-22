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
    public List<Category> list(){
        return categoryRepository.findAllByOrderByListAsc();
    }
}
