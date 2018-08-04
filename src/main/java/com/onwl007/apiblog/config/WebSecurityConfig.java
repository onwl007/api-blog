package com.onwl007.apiblog.config;

import com.onwl007.apiblog.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;


/**
 * @author onwl007@126.com
 * @date 2018/4/27 20:23
 * @desc 安全配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用方法安全配置
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String AUTHORIZATION_TOKEN = "access_token";

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                //自定义获取用户信息
                .userDetailsService(userDetailsService)
                //设置密码加密
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 自定义配置
     *
     * @param http
     * @throws Exception
     */
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/backend/auth/**").permitAll()
                .antMatchers("/backend/**").authenticated()
                .anyRequest().permitAll()
        .and()
        .sessionManagement()//定制我们自己的 session 策略
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//不创建和使用 session

        http.addFilterBefore(genericFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GenericFilterBean genericFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
