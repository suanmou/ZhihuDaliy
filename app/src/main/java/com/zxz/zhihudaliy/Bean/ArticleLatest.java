package com.zxz.zhihudaliy.Bean;

import java.util.List;

//最新文章，包含今日文章与热门文章
//链接:http://news-at.zhihu.com/api/4/news/latest
public class ArticleLatest {
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

    public List<TopStories> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStories> top_stories) {
        this.top_stories = top_stories;
    }

    private String date;

    private List<Stories> stories;

    private List<TopStories> top_stories;
}
