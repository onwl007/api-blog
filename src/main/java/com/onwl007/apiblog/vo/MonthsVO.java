package com.onwl007.apiblog.vo;

/**
 * @author onwl007@126.com
 * @date 2018/4/29 20:44
 * @desc 月份
 */
public class MonthsVO {

    private String month;
    private String monthStr;

    public MonthsVO(String month, String monthStr) {
        this.month = month;
        this.monthStr = monthStr;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonthStr() {
        return monthStr;
    }

    public void setMonthStr(String monthStr) {
        this.monthStr = monthStr;
    }
}
