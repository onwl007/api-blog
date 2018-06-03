package com.onwl007.apiblog.vo;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/6/3 16:39
 * @desc
 */
public class DataArchivesVO {
    private String year;
    private String month;
    private List<ArticlesVO> articles;

    public DataArchivesVO() {
    }

    public DataArchivesVO(String year, String month, List<ArticlesVO> articles) {
        this.year = year;
        this.month = month;
        this.articles = articles;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<ArticlesVO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesVO> articles) {
        this.articles = articles;
    }
}
