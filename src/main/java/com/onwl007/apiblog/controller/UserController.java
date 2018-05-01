package com.onwl007.apiblog.controller;

import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.domain.User;
import com.onwl007.apiblog.service.UserService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author onwl007@126.com
 * @date 2018/5/1 14:10
 * @desc
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResultGenerator generator;

    /**
     * 查询博主信息
     *
     * @return
     */
    @GetMapping("/blogger")
    public RestResult getBlogger() {
        User user = userService.getUserByRole(0);
        if (user != null) {
            return generator.getSuccessResult("查询博主成功", user);
        }
        return generator.getFailResult("查询博主失败", user);
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RestResult getUserById(@PathVariable("id") String id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return generator.getSuccessResult("查询用户成功", user);
        }
        return generator.getFailResult("查询用户失败", user);
    }

    /**
     * 查询站内留言用户列表
     *
     * @return
     */
    @GetMapping("/guests")
    public RestResult getGuests() {
        return generator.getFailResult("查询站内留言用户列表失败", null);
    }
}
