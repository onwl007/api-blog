package com.onwl007.apiblog.page;

import com.onwl007.apiblog.domain.Article;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/5/5 12:57
 * @desc
 */
public class ArticlePagination {
    private List<Article> list;
    private Pagination pagination;

    public ArticlePagination(List<Article> list, Pagination pagination) {
        this.list = list;
        this.pagination = pagination;
    }

    public List<Article> getList() {
        return list;
    }

    public void setList(List<Article> list) {
        this.list = list;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
