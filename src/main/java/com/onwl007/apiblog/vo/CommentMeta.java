package com.onwl007.apiblog.vo;

/**
 * @author onwl007@126.com
 * @date 2018/4/21 16:41
 * @desc 评论元数据
 */
public class CommentMeta {
    //用户ip
    private String ip;
    //ip位置
    private Object location;
    //user-agent
    private String ua;
    private String referer;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }
}
