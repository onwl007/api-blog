package com.onwl007.apiblog.controller.admin;

import com.onwl007.apiblog.domain.Category;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.service.CategoryService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        if (list != null && list.size() > 0) {
            return generator.getSuccessResult("获取全部分类成功", list);
        }
        return generator.getFailResult();
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
