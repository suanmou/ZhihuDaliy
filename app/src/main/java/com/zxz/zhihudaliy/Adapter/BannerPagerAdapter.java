package com.zxz.zhihudaliy.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zxz.zhihudaliy.Listener.OnBannerClickListener;

import java.util.List;

/**
 * Created by suan on 2017/5/14.
 */

public class BannerPagerAdapter extends PagerAdapter {
    private List<ImageView> imageViews;
    private OnBannerClickListener onBannerClickListener;
    public BannerPagerAdapter(List<ImageView> imageViews){
        this.imageViews = imageViews;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
//        return super.instantiateItem(container, position);
        ImageView iv = imageViews.get(position);
        container.addView(iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onBannerClickListener!=null){
                    onBannerClickListener.onClick(position);
                }
            }
        });
        return iv ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    public void setOnBannerClickListener(OnBannerClickListener onBannerClickListener) {
        this.onBannerClickListener = onBannerClickListener;
    }
}
