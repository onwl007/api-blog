package com.onwl007.apiblog.controller.admin;

import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.domain.TokenModel;
import com.onwl007.apiblog.domain.User;
import com.onwl007.apiblog.repository.TokenManager;
import com.onwl007.apiblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author onwl007@126.com
 * @date 2018/6/7 21:13
 * @desc 登录
 */
@RestController
@RequestMapping("/backend")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/auth/local/login")
    public RestResult login(@RequestBody Map<String, Object> map){
        String username=map.get("name").toString();
        String password=map.get("password").toString();
        User user=userRepository.findUserByName(username);
        if (user==null ){

        }
        return null;
    }

}
