package com.onwl007.apiblog.vo;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/29 20:49
 * @desc
 */
public class ListVO {

    private int year;
    private List<MonthsVO> list;

    public ListVO(int year, List<MonthsVO> list) {
        this.year = year;
        this.list = list;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<MonthsVO> getList() {
        return list;
    }

    public void setList(List<MonthsVO> list) {
        this.list = list;
    }
}
