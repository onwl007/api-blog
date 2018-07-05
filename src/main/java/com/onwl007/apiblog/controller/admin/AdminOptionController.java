package com.onwl007.apiblog.controller.admin;

import com.onwl007.apiblog.domain.Option;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.service.OptionService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author onwl007@126.com
 * @date 2018/7/5 22:23
 * @desc 全局参数
 */
@RestController
@CrossOrigin
@RequestMapping("backend")
public class AdminOptionController {

    @Autowired
    OptionService optionService;

    @Autowired
    ResultGenerator generator;

    @GetMapping("/options")
    public RestResult getOptions() {
        Option option = optionService.getOption();
        if (option != null) {
            return generator.getSuccessResult("站内参数获取成功", option);
        }
        return generator.getFailResult("站内参数获取失败", option);
    }

    @PatchMapping("/options")
    public RestResult modifyOptions(@RequestBody Option option) {
        if (option != null) {
            optionService.saveOption(option);
            return generator.getSuccessResult("修改全局设置成功", option);
        }
        return generator.getFailResult("设置失败", null);
    }

}
