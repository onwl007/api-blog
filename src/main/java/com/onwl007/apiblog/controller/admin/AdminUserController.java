package com.onwl007.apiblog.controller.admin;

import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.domain.User;
import com.onwl007.apiblog.service.UserService;
import com.onwl007.apiblog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author onwl007@126.com
 * @date 2018/7/8 10:00
 * @desc 管理员用户
 */
@RestController
@CrossOrigin
@RequestMapping("backend")
public class AdminUserController {

    @Autowired
    UserService userService;

    @Autowired
    ResultGenerator generator;

    /**
     * 修改管理员的相关信息
     *
     * @param id
     * @param user
     * @return
     */
    @PatchMapping("/users/{id}")
    public RestResult saveUserConfig(@PathVariable("id") String id, @RequestBody User user) {
        User admin = userService.getUserById(id);
        admin.setSlogan(user.getSlogan());
        admin.setAvatar(user.getAvatar());
        if (user.getPassword() != null) {
            admin.setPassword(user.getPassword());
        }
        admin.setUpdateAt(new Date());
        userService.updateUser(admin);
        return generator.getSuccessResult("修改管理员信息成功", admin);
    }
}
