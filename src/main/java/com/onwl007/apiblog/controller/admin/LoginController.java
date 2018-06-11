package com.onwl007.apiblog.controller.admin;

import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.domain.TokenModel;
import com.onwl007.apiblog.domain.User;
import com.onwl007.apiblog.repository.UserRepository;
import com.onwl007.apiblog.util.JWT;
import com.onwl007.apiblog.util.ResultGenerator;
import com.onwl007.apiblog.vo.AuthInfoVO;
import com.onwl007.apiblog.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private String name;

    @PostMapping("/auth/local/login")
    public RestResult login(@RequestBody Map<String, Object> map, HttpServletResponse response, HttpServletRequest request){
        String username=map.get("name").toString();
        name=username;
        String password=map.get("password").toString();
        User user=userRepository.findUserByName(username);
        if (user==null || !user.getPassword().equals(password)){
            return generator.getFailResult("用户名或密码错误",null);
        }
        LoginVO loginVO=new LoginVO(user.getId(),user.getName());
        String token= JWT.sign(loginVO,60000);
        System.out.println(token);
        Cookie cookie=new Cookie("jooger.me.token",token);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(0);
        cookie.setSecure(false);
        cookie.setDomain("jooger.me.userId"+user.getId());
        str=token;
        TokenModel model=new TokenModel(user.getId(),token);
        return generator.getSuccessResult("登录成功",model);
    }

    @GetMapping("/auth/info")
    public RestResult loginInfo(){
        User user=userRepository.findUserByName(name);
        AuthInfoVO authInfoVO=new AuthInfoVO(user,str);
        return generator.getSuccessResult("请求成功",authInfoVO);
    }

}
