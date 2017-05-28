package com.zxz.zhihudaliy.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxz.zhihudaliy.Acticity.R;
//import com.zxz.zhihudaliy.R;


//  主题文章顶部图片

public class ThemeArticleTopHolder extends RecyclerView.ViewHolder {

    public ImageView themeImage;

    public TextView themeDescription;

    public ThemeArticleTopHolder(View itemView) {
        super(itemView);
        themeImage = (ImageView) itemView.findViewById(R.id.themeImage);
        themeDescription = (TextView) itemView.findViewById(R.id.themeDescription);
    }
}
