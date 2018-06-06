package com.onwl007.apiblog.domain;

/**
 * @author onwl007@126.com
 * @date 2018/6/6 21:26
 * @desc token
 */
public class TokenModel {

    //用户ID
    private String id;

    //token
    private String token;

    public TokenModel() {
    }

    public TokenModel(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
