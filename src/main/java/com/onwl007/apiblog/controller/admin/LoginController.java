package com.onwl007.apiblog.controller.admin;

import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.domain.TokenModel;
import com.onwl007.apiblog.domain.User;
import com.onwl007.apiblog.repository.TokenManager;
import com.onwl007.apiblog.repository.UserRepository;
import com.onwl007.apiblog.util.JWT;
import com.onwl007.apiblog.util.ResultGenerator;
import com.onwl007.apiblog.vo.AuthInfoVO;
import com.onwl007.apiblog.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.Map;

/**
 * @author onwl007@126.com
 * @date 2018/6/7 21:13
 * @desc 登录
 */
@CrossOrigin
@RestController
@RequestMapping("backend")
public class LoginController {

    @Autowired
    private ResultGenerator generator;

    @Autowired
    private UserRepository userRepository;

    private String str;

    @PostMapping("/auth/local/login")
    public RestResult login(@RequestBody Map<String, Object> map){
        String username=map.get("name").toString();
        String password=map.get("password").toString();
        User user=userRepository.findUserByName(username);
        if (user==null || !user.getPassword().equals(password)){
            return generator.getFailResult("用户名或密码错误",null);
        }
        LoginVO loginVO=new LoginVO(user.getId(),user.getName());
        String token= JWT.sign(loginVO,60000L*60L*24L*7L);
        str=token;
        TokenModel model=new TokenModel(user.getId(),token);
        return generator.getSuccessResult("登录成功",model);
    }

    @GetMapping("/auth/info")
    public RestResult loginInfo(){
        User user=userRepository.findUserByName("Jooger");
        AuthInfoVO authInfoVO=new AuthInfoVO(user,str);
        return generator.getSuccessResult("请求成功",authInfoVO);
    }

}
