package com.zxz.zhihudaliy.Listener;

//加载文章主题列表事件监听

import com.zxz.zhihudaliy.Bean.Others;

import java.util.List;

public interface OnLoadThemesListener {
    void onSuccess(List<Others> othersList);

    void onFailure();
}
