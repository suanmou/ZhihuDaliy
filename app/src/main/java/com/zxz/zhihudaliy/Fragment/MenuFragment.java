package com.zxz.zhihudaliy.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zxz.zhihudaliy.Acticity.MainActivity;
import com.zxz.zhihudaliy.Acticity.R;
import com.zxz.zhihudaliy.Adapter.ArticleThemeListAdapter;
import com.zxz.zhihudaliy.Bean.Others;
import com.zxz.zhihudaliy.Bean.Theme;
import com.zxz.zhihudaliy.Listener.OnLoadThemesListener;
import com.zxz.zhihudaliy.Net.HttpUtil;
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
        homePage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                MainActivity mainActivity =getRootActivity();
                if(!mainActivity.isHomepage){
                    mainActivity.getHomepage();
                }
                mainActivity.closeDrawerLayout();
            }
        });


        //获取文章主题事件监听
        OnLoadThemesListener listener = new OnLoadThemesListener() {
            @Override
            public void onSuccess(List<Others> othersList) {
                themeList.clear();
                themeList.addAll(othersList);
                themeStringList.clear();
                for (int i = 0; i < othersList.size(); i++) {
                    themeStringList.add(othersList.get(i).getName());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure() {

            }
        };
        themesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int themeId = themeList.get(position).getId();
                if (themeId != getRootActivity().getCurrentId()) {
                    String title = themeList.get(position).getName();
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", themeId);
                    bundle.putString("Title", title);
                    getRootActivity().getThemeFragment(themeId, bundle);
                }
                getRootActivity().closeDrawerLayout();
            }

        });
        HttpUtil.getThemes(listener);
    }
}
