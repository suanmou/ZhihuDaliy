package com.zxz.zhihudaliy.Listener;

import com.zxz.zhihudaliy.Bean.ArticleLatest;
//加载最新文章事件监听

public interface OnLoadLatestArticleListener {
    void onSuccess(ArticleLatest articleLatest);

    void onFailure();
}
