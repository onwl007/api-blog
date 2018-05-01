package com.onwl007.apiblog.controller;

import com.onwl007.apiblog.domain.Moment;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.service.MomentService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/5/1 14:41
 * @desc
 */
@RestController
@RequestMapping("moments")
public class MomentController {

    @Autowired
    private MomentService momentService;

    @Autowired
    private ResultGenerator generator;

    /**
     * 获取动态列表
     *
     * @return
     */
    @GetMapping
    public RestResult getMoments() {
        List<Moment> list = momentService.listMoments();
        if (list != null && list.size() > 0) {
            return generator.getSuccessResult("动态列表获取成功", list);
        }
        return generator.getFailResult("动态列表获取失败", list);
    }
}
