package com.onwl007.apiblog.util;

/**
 * @author onwl007@126.com
 * @date 2018/4/26 20:43
 * @desc 状态码
 */
public enum CodeMap {
    SUCCESS(200),//成功
    REQUEST_FAIL(-1),//请求失败
    UNAUTHORIZED(401),//权限校验错误
    FORBIDDEN(403),//禁止访问
    SERVER_ERROR(500),//服务器错误
    PARAMETER_ERROR(10001);//参数错误

    private int code;

    CodeMap(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
