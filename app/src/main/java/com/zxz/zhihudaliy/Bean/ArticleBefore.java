package com.zxz.zhihudaliy.Bean;

import java.util.List;

//过往文章，链接后缀加上日期即可获取内容
//链接：http://news.at.zhihu.com/api/4/news/before/20170515
public class ArticleBefore {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    private String date;

    private List<Stories> stories;
}
