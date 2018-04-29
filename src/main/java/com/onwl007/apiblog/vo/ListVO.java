package com.onwl007.apiblog.vo;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/29 20:49
 * @desc
 */
public class ListVO {

    private int year;
    private List<ListVO> list;

    public ListVO(int year, List<ListVO> list) {
        this.year = year;
        this.list = list;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<ListVO> getList() {
        return list;
    }

    public void setList(List<ListVO> list) {
        this.list = list;
    }
}
