package com.onwl007.apiblog.controller;

import com.onwl007.apiblog.domain.Category;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.service.CategoryService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/5/1 13:36
 * @desc
 */
@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ResultGenerator generator;

    @GetMapping
    public RestResult listCategories(){
        List<Category> list=categoryService.listCategory();
        if (list!=null && list.size()>0){
            return generator.getSuccessResult("获取全部分类成功",list);
        }
        return generator.getFailResult();
    }
}
