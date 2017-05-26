package com.zxz.zhihudaliy.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxz.zhihudaliy.Acticity.R;

/**
 * Created by ZY on 2016/7/31.
 * 主题文章
 */
public class ThemeFragment extends BaseFragment {

    //文章列表
    private RecyclerView recyclerView;
//
//    private ArticleThemeContentAdapter adapter;
//
//    private OnLoadThemeContentListener listener;

    private int id;

    private boolean flag;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_article_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.articleList);
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        return view;
    }


}
