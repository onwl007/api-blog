package com.onwl007.apiblog.controller.admin;

import com.onwl007.apiblog.domain.Category;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.service.CategoryService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.Date;
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

    /**
     * 查询分类
     *
     * @param keyword
     * @return
     */
    @GetMapping("/categories")
    public RestResult listCategories(@RequestParam(value = "keyword", required = false) String keyword) {

        if (keyword != null && !keyword.equals("")) {
            Category category = categoryService.getCategortByName(keyword);
            return generator.getSuccessResult("查询分类成功", category);
        }

        List<Category> list = categoryService.listCategory();
        return generator.getSuccessResult("获取全部分类成功", list);
    }

    /**
     * 创建分类
     *
     * @param category
     * @return
     */
    @PostMapping("/categories")
    public RestResult addCategory(@RequestBody Category category) {
        if (category != null) {
            Boolean repeat = categoryService.isExistCategory(category.getName());
            if (repeat) {
                return generator.getFailResult("[" + category.getName() + "]已经存在", null);
            } else {
                category.setCreateAt(new Date());
                categoryService.createCategory(category);
                return generator.getSuccessResult("创建分类成功", category);
            }
        }
        return generator.getFailResult("创建分类失败", null);
    }

    /**
     * 修改分类
     *
     * @param id
     * @param modify
     * @return
     */
    @PatchMapping("/categories/{id}")
    public RestResult modifyCategory(@PathVariable String id, @RequestBody Category modify) {
        Category category=categoryService.getCategoryById(id);
        if (modify != null && category!=null) {
            category.setName(modify.getName());
            category.setDescription(modify.getDescription());
            category.setUpdateAt(new Date());
            categoryService.createCategory(category);
            return generator.getSuccessResult("修改分类成功", category);
        }
        return generator.getFailResult("修改分类失败", null);
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @DeleteMapping("/categories/{id}")
    public RestResult deleteCategory(@PathVariable String id) {
        if (id != null && !id.equals("")) {
            categoryService.deleteCategory(id);
            return generator.getSuccessResult("删除分类成功");
        }
        return generator.getFailResult("删除分类失败", null);
    }
}
