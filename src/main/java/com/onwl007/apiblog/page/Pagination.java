package com.onwl007.apiblog.page;

/**
 * @author onwl007@126.com
 * @date 2018/5/4 22:39
 * @desc 自定义分页
 */
public class Pagination {

    //数据总数
    private Long total;
    //当前页
    private Integer cur_page;
    //总共多少页
    private Integer total_page;
    //当前页面条数
    private Integer per_page;

    public Pagination() {
    }

    public Pagination(Long total, Integer cur_page, Integer total_page, Integer per_page) {
        this.total = total;
        this.cur_page = cur_page;
        this.total_page = total_page;
        this.per_page = per_page;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getCur_page() {
        return cur_page;
    }

    public void setCur_page(Integer cur_page) {
        this.cur_page = cur_page;
    }

    public Integer getTotal_page() {
        return total_page;
    }

    public void setTotal_page(Integer total_page) {
        this.total_page = total_page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }
}
