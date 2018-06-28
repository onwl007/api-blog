package com.onwl007.apiblog.controller.admin;

import com.onwl007.apiblog.config.Constants;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.domain.Tag;
import com.onwl007.apiblog.service.TagService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/6/10 20:15
 * @desc 后台查询 tag
 */
@RestController
@RequestMapping("backend")
public class AdminTagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ResultGenerator generator;

    /**
     * 查询标签列表
     *
     * @param keyword
     * @return
     */
    @GetMapping("/tags")
    public RestResult listTag(@RequestParam(value = "keyword", required = false) String keyword) {

        if (keyword != null && !keyword.equals("")) {
            Tag tag = tagService.getTagByName(keyword);
            return generator.getSuccessResult("查询标签成功", tag);
        }

        List<Tag> tags = tagService.listTags();
        if (tags != null && tags.size() > 0) {
            return generator.getSuccessResult("获取全部标签成功", tags);
        }
        return generator.getFailResult();
    }

    /**
     * 删除标签
     *
     * @param id
     * @return
     */
    @DeleteMapping("/tags/{id}")
    public RestResult deleteTag(@PathVariable String id) {
        if (id != null && !id.equals("")) {
            tagService.deleteById(id);
            return generator.getSuccessResult("删除标签成功");
        }
        return generator.getFailResult("删除标签失败", null);
    }
}
