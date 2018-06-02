package com.onwl007.apiblog.vo;

import com.onwl007.apiblog.domain.User;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/6/2 15:19
 * @desc 站内留言用户数据和总数
 */
public class GuestUserVO {
    private List<User> list;
    private int total;

    public GuestUserVO(List<User> list, int total) {
        this.list = list;
        this.total = total;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
