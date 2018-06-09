package com.onwl007.apiblog.vo;

/**
 * @author onwl007@126.com
 * @date 2018/6/9 21:22
 * @desc
 */
public class LoginVO {
    private String id;
    private String name;

    public LoginVO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
