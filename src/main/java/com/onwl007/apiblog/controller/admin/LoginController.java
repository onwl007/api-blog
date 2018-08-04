package com.onwl007.apiblog.controller.admin;

import com.onwl007.apiblog.config.WebSecurityConfig;
import com.onwl007.apiblog.domain.RestResult;
import com.onwl007.apiblog.domain.TokenModel;
import com.onwl007.apiblog.domain.User;
import com.onwl007.apiblog.repository.UserRepository;
import com.onwl007.apiblog.util.JWT;
import com.onwl007.apiblog.util.JWTTokenUtils;
import com.onwl007.apiblog.util.ResultGenerator;
import com.onwl007.apiblog.vo.AuthInfoVO;
import com.onwl007.apiblog.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    private String str;

    private String name;

    @PostMapping("/auth/local/login")
    public RestResult login(@RequestBody Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception{
        String username=map.get("name").toString();
        name=username;
        String password=map.get("password").toString();

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        if (Objects.nonNull(authenticationToken)){
            userRepository.findByName(authenticationToken.getPrincipal().toString())
                    .orElseThrow(()->new Exception("用户不存在"));
        }
        try {
            User user=userRepository.findUserByName(username);
            Authentication authentication=authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token=jwtTokenUtils.createToken(authentication,false,user.getId());
            response.addHeader(WebSecurityConfig.AUTHORIZATION_HEADER,token);
            str=token;
            TokenModel model=new TokenModel(user.getId(),token);
            return generator.getSuccessResult("登录成功",model);
        }catch (BadCredentialsException authentication){
            throw new Exception("密码错误");
        }
    }

    @GetMapping("/auth/info")
    public RestResult loginInfo(){
        User user=userRepository.findUserByName(name);
        AuthInfoVO authInfoVO=new AuthInfoVO(user,str);
        return generator.getSuccessResult("请求成功",authInfoVO);
    }

}
