package com.onwl007.apiblog.config;


import com.onwl007.apiblog.domain.User;
import com.onwl007.apiblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author onwl007@126.com
 * @date 2018/7/29 15:39
 * @desc
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //从数据库中加载用户对象
        Optional<User> user = userRepository.findByName(s);
        //调试用，如果值存在则输出下用户名与密码
        user.ifPresent((value) -> System.out.println("用户名:" + value.getName() + " 用户密码：" + value.getPassword()));
        //若值不再则返回null
        return new MyUserDetails(user.orElse(null));
    }
}
