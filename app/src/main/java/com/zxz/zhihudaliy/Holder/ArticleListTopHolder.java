package com.zxz.zhihudaliy.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zxz.zhihudaliy.Acticity.R;
import com.zxz.zhihudaliy.View.Banner;

/**
 * Created by suan on 2017/5/26.
 * banner轮播图
 */

public class ArticleListTopHolder extends RecyclerView.ViewHolder{
    public Banner banner;

    public ArticleListTopHolder(View itemView) {
        super(itemView);
        banner = (Banner)itemView.findViewById(R.id.banner);
    }
}
