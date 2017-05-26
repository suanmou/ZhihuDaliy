package com.zxz.zhihudaliy.Bean;

import java.util.List;

/**
 * Created by ZY on 2016/7/27.
 * 文章主题列表
 * 链接：http://news-at.zhihu.com/api/4/themes
 */
public class Theme {

    private int limit;

    private List<?> subscribed;

    private List<Others> others;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<?> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<?> subscribed) {
        this.subscribed = subscribed;
    }

    public List<Others> getOthers() {
        return others;
    }

    public void setOthers(List<Others> others) {
        this.others = others;
    }

}
