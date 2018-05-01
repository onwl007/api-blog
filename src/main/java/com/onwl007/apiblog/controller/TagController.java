package com.onwl007.apiblog.controller;

import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.domain.Tag;
import com.onwl007.apiblog.service.TagService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/5/1 14:05
 * @desc
 */
@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ResultGenerator generator;

    @GetMapping
    public RestResult listTag() {
        List<Tag> tags = tagService.listTags();
        if (tags != null && tags.size() > 0) {
            return generator.getSuccessResult("获取全部标签成功", tags);
        }
        return generator.getFailResult();
    }
}
