package com.onwl007.apiblog.vo;

import com.onwl007.apiblog.domain.User;

/**
 * @author onwl007@126.com
 * @date 2018/6/9 16:05
 * @desc
 */
public class AuthInfoVO {
    private User info;
    private String token;

    public AuthInfoVO(User info, String token) {
        this.info = info;
        this.token = token;
    }

    public User getInfo() {
        return info;
    }

    public void setInfo(User info) {
        this.info = info;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
