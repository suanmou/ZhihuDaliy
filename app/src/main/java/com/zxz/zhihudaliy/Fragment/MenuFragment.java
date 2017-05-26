package com.zxz.zhihudaliy.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zxz.zhihudaliy.Acticity.R;
import com.zxz.zhihudaliy.Adapter.ArticleThemeListAdapter;
import com.zxz.zhihudaliy.Bean.Others;
import com.zxz.zhihudaliy.Bean.Theme;
import com.zxz.zhihudaliy.Utility.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/5/14.
 */

public class MenuFragment extends BaseFragment{
    private ArticleThemeListAdapter adapter;
    private TextView homePage;
    private ListView themesListView;
    private List<Others> themeList;
    private List<String> themeStringList;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drawer_menu,container,false);
        homePage =(TextView) view.findViewById((R.id.homepage));
        themesListView=(ListView) view.findViewById(R.id.themeList);
        return view;
    }

    @Override
    protected void initData() {
        Theme theme = JSON.parseObject(Constant.DefaultThemesJson,Theme.class);
        themeList = theme.getOthers();
        themeStringList =new ArrayList<>();
        for(int i =0; i<themeList.size();i++){
            themeStringList.add(themeList.get(i).getName());
        }
        adapter=new ArticleThemeListAdapter(mActivity,themeStringList);
        themesListView.setAdapter(adapter);
//        homePage.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                MainActivity mainActivity =getRootActivity();
//                if(!mainActivity.isHomepage){
//                    mainActivity.getHomepage();
//                }
//                mainActivity.closeDrawerLayout();
//            }
//        });
    }
}
