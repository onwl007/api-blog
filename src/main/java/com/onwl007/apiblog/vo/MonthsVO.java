package com.onwl007.apiblog.vo;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/29 20:44
 * @desc 月份
 */
public class MonthsVO {

    private int month;
    private String monthStr;
    private List<ArticlesVO> articles;

    public MonthsVO(int month, String monthStr, List<ArticlesVO> articles) {
        this.month = month;
        this.monthStr = monthStr;
        this.articles = articles;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getMonthStr() {
        return monthStr;
    }

    public void setMonthStr(String monthStr) {
        this.monthStr = monthStr;
    }

    public List<ArticlesVO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesVO> articles) {
        this.articles = articles;
    }
}
