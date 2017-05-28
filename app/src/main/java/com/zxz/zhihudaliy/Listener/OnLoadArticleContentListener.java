package com.zxz.zhihudaliy.Listener;

import com.zxz.zhihudaliy.Bean.ArticleContent;

//加载文章内容事件监听
public interface OnLoadArticleContentListener {
    void onSuccess(ArticleContent content);

    void onFailure();
}
