package com.zxz.zhihudaliy.Listener;

//加载顶部Banner文章事件监听

import com.zxz.zhihudaliy.Bean.TopStories;

import java.util.List;

public interface OnLoadTopArticleListener {
    void onSuccess(List<TopStories> topStoriesList);

    void onFailure();
}
