package com.zxz.zhihudaliy.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

//import com.zxz.zhihudaliy.R;

import com.zxz.zhihudaliy.Acticity.R;

public class ArticleListFooterHolder extends RecyclerView.ViewHolder {
    public TextView footerTitle;

    public ArticleListFooterHolder(View itemView) {
        super(itemView);
        footerTitle = (TextView) itemView.findViewById(R.id.footerTitle);
    }
}
