package com.onwl007.apiblog.util;

/**
 * @author onwl007@126.com
 * @date 2018/4/27 22:45
 * @desc 自定义异常处理
 */
public class ServiceException extends RuntimeException{

    private int code;
    private String desc;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public ServiceException(int code, String desc) {
        super(desc);
        this.code = code;
        this.desc = desc;
    }

    public ServiceException(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
