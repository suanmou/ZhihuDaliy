package com.zxz.zhihudaliy.Listener;


import com.zxz.zhihudaliy.Bean.ArticleTheme;

 //  加载特定主题下文章列表事件监听
public interface OnLoadThemeContentListener {

    void onSuccess(ArticleTheme articleTheme);

    void onFailure();
}
