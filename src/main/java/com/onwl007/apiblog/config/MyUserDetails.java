package com.onwl007.apiblog.config;


import com.onwl007.apiblog.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * @author onwl007@126.com
 * @date 2018/7/29 15:33
 * @desc
 */
public class MyUserDetails implements UserDetails{

    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = user.getRole_name();
        List<GrantedAuthority> authorities = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (roles!=null){
            authorities.add(new SimpleGrantedAuthority(roles));
            return authorities;
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList("");
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
