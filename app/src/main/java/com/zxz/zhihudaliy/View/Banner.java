package com.zxz.zhihudaliy.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.utils.L;
import com.zxz.zhihudaliy.Acticity.ArticleContentActivity;
import com.zxz.zhihudaliy.Acticity.R;
import com.zxz.zhihudaliy.Adapter.BannerPagerAdapter;
import com.zxz.zhihudaliy.Bean.TopStories;
import com.zxz.zhihudaliy.Listener.OnBannerClickListener;
import com.zxz.zhihudaliy.Utility.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by suan on 2017/5/14.
 */

public class Banner extends FrameLayout {
    private ViewPager viewPager;
//    左右滑动组件
    private BannerPagerAdapter adapter;
//    bannerPagerAdapter的适配器
    private TextView topStoriesTitle;
    private List<ImageView> imageViews;
    private List<TopStories> topStoriesList;
    private List<View> dotList;
//    实现线程的通信
//    private Handler handler;
    private android.os.Handler handler;
//    线程
    private Runnable runnable;
    private Context context;
    private OnBannerClickListener onBannerClickListener;
    private int currentItem = 0 ;
    public Banner(Context context){
        this(context,null);
    }
    public Banner(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }
    public Banner(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        this.context = context;
        init();
    }
    public  void init(){
        View view  = LayoutInflater.from(context).inflate(R.layout.banner,this,false);
        viewPager= (ViewPager) view.findViewById(R.id.viewPager);
        topStoriesList = getDefaultBannerList();
        dotList = new ArrayList<>();
        handler = new android.os.Handler();
        topStoriesTitle = (TextView)view.findViewById(R.id.articleTitle);
        View dot0 = view.findViewById(R.id.v_dot0);
        View dot1 = view.findViewById(R.id.v_dot1);
        View dot2 = view.findViewById(R.id.v_dot2);
        View dot3 = view.findViewById(R.id.v_dot3);
        View dot4 = view.findViewById(R.id.v_dot4);
        dotList.add(dot0);
        dotList.add(dot1);
        dotList.add(dot2);
        dotList.add(dot3);
        dotList.add(dot4);
        onBannerClickListener = new OnBannerClickListener() {
            @Override
            public void onClick(int id) {
                id = topStoriesList.get(id).getId();
                Intent intent = new Intent(context, ArticleContentActivity.class);
                Bundle bundle  = new Bundle();
                bundle.putInt("ID",id);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        };
        runnable = new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(currentItem);
                currentItem = (currentItem+1)%imageViews.size();
                handler.postDelayed(this, 2500);

            }
        };
        reset();
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(view, layoutParams);
    }
    private void reset(){
//        imageViews = new ArrayList<>();
//    for(int i = 0 ; i <topStoriesList.size();i++){
////         取前五张图片
//        if(i>4){
//            break;
//        }
//        ImageView imageView = new ImageView(context);
//        imageView.setImageResource(R.drawable.default_image);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
////            Constant.getImageLoader().
//    }
//}private void reset() {
            imageViews = new ArrayList<>();
            for (int i = 0; i < topStoriesList.size(); i++) {
//                只取前五张图片
                if (i > 4) {
                    break;
                }
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(R.drawable.default_image);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Constant.getImageLoader().displayImage(topStoriesList.get(i).getImage(),
                        imageView, Constant.getDisplayImageOptions());
                imageViews.add(imageView);
                dotList.get(i).setVisibility(View.VISIBLE);
            }
            adapter = new BannerPagerAdapter(imageViews);
            adapter.setOnBannerClickListener(onBannerClickListener);
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new MyPageChangeListener());
        }
    public void update(List<TopStories> list){
        topStoriesList.clear();
        topStoriesList = list;
        reset();
        adapter.notifyDataSetChanged();
        topStoriesTitle.setText(topStoriesList.get(0).getTitle());
    }
    public void startPlay(){
        cancelPlay();
        handler.postDelayed(runnable,2000);
    }
    public  void cancelPlay(){
        handler.removeCallbacks(runnable);
    }
    private  class  MyPageChangeListener implements ViewPager.OnPageChangeListener{
        private int oldPosition = 0;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            TopStories topStories = topStoriesList.get(position);
            topStoriesTitle.setText(topStories.getTitle());
            dotList.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dotList.get(position).setBackgroundResource(R.drawable.dot_selected);
            oldPosition = position;
        }
    }
    private List<TopStories> getDefaultBannerList(){
        List<TopStories> topStoriesList = new ArrayList<>();
        TopStories topStories = new TopStories();
        topStories.setTitle("享受阅读的乐趣");
        topStories.setImage("0");
        topStories.setId(0);
        topStoriesList.add(topStories);
        return  topStoriesList;
    }

}
