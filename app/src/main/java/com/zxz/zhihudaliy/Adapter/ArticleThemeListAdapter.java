package com.zxz.zhihudaliy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zxz.zhihudaliy.Acticity.R;

import java.util.List;

/**
 * 侧滑菜单文章主题列表Adapter
 * Created by pc on 2017/5/14.
 */

public class ArticleThemeListAdapter extends BaseAdapter{

    private List<String> themeList;
    private Context context;
    public ArticleThemeListAdapter(Context context,List<String> themeList){
        this.context=context;
        this.themeList = themeList;
    }
    @Override
    public int getCount() {
        return themeList.size();
    }

    @Override
    public Object getItem(int position) {
        return themeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
       if(converView==null){
           converView = LayoutInflater.from(context).inflate(R.layout.drawer_menu_themes_item, parent, false);
       }
        TextView tv_item = (TextView) converView.findViewById(R.id.tv_item);
        tv_item.setText(themeList.get(position));
        return converView;
    }
}
