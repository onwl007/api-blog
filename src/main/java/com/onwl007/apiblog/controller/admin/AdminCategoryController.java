package com.onwl007.apiblog.controller.admin;

import com.onwl007.apiblog.domain.Category;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.service.CategoryService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/6/10 16:53
 * @desc
 */
@RestController
@CrossOrigin
@RequestMapping("backend")
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ResultGenerator generator;

    @GetMapping("/categories")
    public RestResult listCategories(){
        List<Category> list=categoryService.listCategory();
        if (list!=null && list.size()>0){
            return generator.getSuccessResult("获取全部分类成功",list);
        }
        return generator.getFailResult();
    }
}
