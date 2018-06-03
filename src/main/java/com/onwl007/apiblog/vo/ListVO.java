package com.onwl007.apiblog.vo;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/29 20:49
 * @desc
 */
public class ListVO {

    private String year;
    private List<MonthsVO> months;

    public ListVO() {
    }

    public ListVO(String year, List<MonthsVO> months) {
        this.year = year;
        this.months = months;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<MonthsVO> getMonths() {
        return months;
    }

    public void setMonths(List<MonthsVO> months) {
        this.months = months;
    }
}
