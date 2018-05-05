package com.onwl007.apiblog.page;

import com.onwl007.apiblog.domain.Comment;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/5/5 16:14
 * @desc
 */
public class CommentPagination {
    private List<Comment> list;
    private Pagination pagination;

    public CommentPagination(List<Comment> list, Pagination pagination) {
        this.list = list;
        this.pagination = pagination;
    }

    public List<Comment> getList() {
        return list;
    }

    public void setList(List<Comment> list) {
        this.list = list;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
