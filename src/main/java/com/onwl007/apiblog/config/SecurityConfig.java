package com.onwl007.apiblog.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author onwl007@126.com
 * @date 2018/4/27 20:23
 * @desc 安全配置类
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用方法安全配置
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * 自定义配置
     * @param http
     * @throws Exception
     */
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/backend/**").permitAll()
                .anyRequest().permitAll();
    }

}
