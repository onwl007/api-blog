package com.onwl007.apiblog.controller;

import com.onwl007.apiblog.domain.Option;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.service.OptionService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author onwl007@126.com
 * @date 2018/5/1 14:37
 * @desc
 */
@RestController
@RequestMapping("options")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @Autowired
    private ResultGenerator generator;

    /**
     * 获取站内参数
     *
     * @return
     */
    @GetMapping
    public RestResult getOptions() {
        Option option = optionService.getOption();
        if (option != null) {
            return generator.getSuccessResult("站内参数获取成功", option);
        }
        return generator.getFailResult("站内参数获取失败", option);
    }
}
