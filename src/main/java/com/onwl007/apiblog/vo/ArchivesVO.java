package com.onwl007.apiblog.vo;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/29 20:51
 * @desc 文章归档
 */
public class ArchivesVO {

    private int count;
    private List<ListVO> list;

    public ArchivesVO(int count, List<ListVO> list) {
        this.count = count;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListVO> getList() {
        return list;
    }

    public void setList(List<ListVO> list) {
        this.list = list;
    }
}
