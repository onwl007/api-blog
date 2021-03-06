package com.onwl007.apiblog.vo;

/**
 * @author onwl007@126.com
 * @date 2018/4/21 16:29
 * @desc 文章元数据 （浏览量，喜欢数，评论数）
 */
public class ArticleMeta {
    //文章浏览量
    private int pvs;
    //文章喜欢数
    private int ups;
    //文章评论数
    private int comments;

    public ArticleMeta() {
    }

    public ArticleMeta(int pvs, int ups, int comments) {
        this.pvs = pvs;
        this.ups = ups;
        this.comments = comments;
    }

    public int getPvs() {
        return pvs;
    }

    public void setPvs(int pvs) {
        this.pvs = pvs;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
