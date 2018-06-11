package com.onwl007.apiblog.controller.admin;

import com.onwl007.apiblog.config.Constants;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.domain.Tag;
import com.onwl007.apiblog.service.TagService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/tags")
    public RestResult listTag(HttpServletRequest request) {
        System.out.println(request.getHeader("Cookie"));
        List<Tag> tags = tagService.listTags();
        if (tags != null && tags.size() > 0) {
            return generator.getSuccessResult("获取全部标签成功", tags);
        }
        return generator.getFailResult();
    }
}
