package com.zxz.zhihudaliy.Acticity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zxz.zhihudaliy.Fragment.BaseFragment;
import com.zxz.zhihudaliy.Fragment.MainFragment;
import com.zxz.zhihudaliy.Fragment.ThemeFragment;
import android.widget.Toast;
import com.zxz.zhihudaliy.Net.HttpUtil;
//import com.zxz.zhihudaliy.R;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private SwipeRefreshLayout refreshLayout;
    private static final int REQUEST_CODE_GO_TO_REGIST = 100;
    //文章的发布日期
    private String date;

    //用来实现再按一次退出程序的效果
    private boolean isExit;

    private int currentId;

    public boolean isHomepage;
//    夜间模式实现

//    将主题设置保存到SharePreferences的工具

    private RecyclerView mRecyclerView;
    private LinearLayout mHeaderLayout;
    private List<RelativeLayout> mLayoutList;
    private List<LinearLayout> mLinearLayoutList;

    private List<TextView> mTextViewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        currentId = -1;
        getTransition().add(R.id.fl_content, new MainFragment(), "Fragment" + currentId).commit();
        isHomepage = true;
        final TextView txtViewLogin = (TextView)findViewById(R.id.tv_login);

        txtViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = txtViewLogin.getText().toString();
                if(a.equals("请登录")){
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_GO_TO_REGIST);
                }else{
                    Toast.makeText(MainActivity.this,"已成功登陆",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("享受阅读的乐趣");
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_red_light);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isHomepage) {
                    MainFragment mainFragment = (MainFragment) getFragmentByTag("Fragment" + currentId);
                    mainFragment.getLatestArticleList();
                } else {
                    ThemeFragment themeFragment = (ThemeFragment) getFragmentByTag("Fragment" + currentId);
                    themeFragment.refreshData();
                }
            }
        });
    }
    //新添加！！！！！！菜单功能
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_item1:

                break;
            case R.id.action_item2:

                break;
            default:break;
        }
        return true;

    }

    //获取主页
    public void getHomepage() {
        MainFragment mainFragment = (MainFragment) getFragmentByTag("Fragment" + "-1");
        ThemeFragment themeFragment = (ThemeFragment) getFragmentByTag("Fragment" + currentId);
        FragmentTransaction transition = getTransition();
        transition.hide(themeFragment);
        if (mainFragment == null) {
            transition.add(R.id.fl_content, new MainFragment(), "Fragment" + "-1").commit();
        } else {
            transition.show(mainFragment).commit();
        }
        currentId = -1;
        isHomepage = true;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("享受阅读的乐趣");
        }
    }

    //获取主题Fragment
    public void getThemeFragment(int id, Bundle bundle) {
        ThemeFragment toFragment = (ThemeFragment) getFragmentByTag("Fragment" + id);
        BaseFragment nowFragment;
        if (isHomepage) {
            nowFragment = (MainFragment) getFragmentByTag("Fragment" + currentId);
        } else {
            nowFragment = (ThemeFragment) getFragmentByTag("Fragment" + currentId);
        }
        FragmentTransaction transition = getTransition();
        transition.hide(nowFragment);
        if (toFragment == null) {
            ThemeFragment themeFragment = new ThemeFragment();
            themeFragment.setArguments(bundle);
            transition.add(R.id.fl_content, themeFragment, "Fragment" + id).commit();
        } else {
            transition.show(toFragment).commit();
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(bundle.getString("Title"));
            }
        }
        currentId = id;
        isHomepage = false;
        setRefresh(false);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawerLayout();
            return;
        }
        if (!isHomepage) {
            getHomepage();
            return;
        }
        if (isExit) {
            refreshLayout.setRefreshing(false);
            HttpUtil.client.cancelAllRequests(true);
            super.onBackPressed();
        } else {
            hint();
        }
    }

    private void hint() {
        Snackbar snackbar = Snackbar.make(refreshLayout, "再按一次退出", Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(Color.parseColor("#0099CC"));
        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                isExit = false;
            }

            @Override
            public void onShown(Snackbar snackbar) {
                isExit = true;
            }
        }).show();
    }

    private FragmentTransaction getTransition() {
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        return transition;
    }

    public Fragment getFragmentByTag(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    public int getCurrentId() {
        return currentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRefresh(boolean flag) {
        refreshLayout.setRefreshing(flag);
    }

    public void closeDrawerLayout() {
        this.drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void joke(View view) {
        Snackbar snackbar = Snackbar.make(refreshLayout, "其实并没有该功能，只是为了占个地方。。。", Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(Color.parseColor("#0099CC"));
        snackbar.show();
    }
    //
//    private void changeThemeByZhiHu() {
//        showAnimation();
//        toggleThemeSetting();
//        refreshUI();
//    }
//    /**
//     * 刷新UI界面
//     */
//    private void refreshUI() {
//        TypedValue background = new TypedValue();//背景色
//        TypedValue textColor = new TypedValue();//字体颜色
//        Resources.Theme theme = getTheme();
//        theme.resolveAttribute(R.attr.colorBackground, background, true);
//        theme.resolveAttribute(R.attr.colorTextColor, textColor, true);
//        toolbar.setBackgroundResource(background.resourceId);
//        drawerLayout.setBackgroundResource(background.resourceId);
//        refreshStatusBar();
//        drawerLayout.setBackgroundResource(background.resourceId);
//        refreshLayout.setBackgroundResource(background.resourceId);
//        toolbar.setBackgroundResource(background.resourceId);
//        mHeaderLayout.setBackgroundResource(background.resourceId);
//        for (RelativeLayout layout : mLayoutList) {
//            layout.setBackgroundResource(background.resourceId);
//        }
//        for (TextView textView : mTextViewList) {
//            textView.setBackgroundResource(background.resourceId);
//        }
//        Resources resources = getResources();
//        for (TextView textView : mTextViewList) {
//            textView.setTextColor(resources.getColor(textColor.resourceId));
//        }
//
//        int childCount = mRecyclerView.getChildCount();
//        for (int childIndex = 0; childIndex < childCount; childIndex++) {
//            ViewGroup childView = (ViewGroup) mRecyclerView.getChildAt(childIndex);
//            childView.setBackgroundResource(background.resourceId);
////            View infoLayout = childView.findViewById(R.id.info_layout);
////            infoLayout.setBackgroundResource(background.resourceId);
////            TextView nickName = (TextView) childView.findViewById(R.id.tv_nickname);
////            nickName.setBackgroundResource(background.resourceId);
////            nickName.setTextColor(resources.getColor(textColor.resourceId));
////            TextView motto = (TextView) childView.findViewById(R.id.tv_motto);
////            motto.setBackgroundResource(background.resourceId);
////            motto.setTextColor(resources.getColor(textColor.resourceId));
//        }
//
//        //让 RecyclerView 缓存在 Pool 中的 Item 失效
//        //那么，如果是ListView，要怎么做呢？这里的思路是通过反射拿到 AbsListView 类中的 RecycleBin 对象，然后同样再用反射去调用 clear 方法
//        Class<RecyclerView> recyclerViewClass = RecyclerView.class;
//        try {
//            Field declaredField = recyclerViewClass.getDeclaredField("mRecycler");
//            declaredField.setAccessible(true);
//            Method declaredMethod = Class.forName(RecyclerView.Recycler.class.getName()).getDeclaredMethod("clear", (Class<?>[]) new Class[0]);
//            declaredMethod.setAccessible(true);
//            declaredMethod.invoke(declaredField.get(mRecyclerView), new Object[0]);
//            RecyclerView.RecycledViewPool recycledViewPool = mRecyclerView.getRecycledViewPool();
//            recycledViewPool.clear();
//
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        refreshStatusBar();
//    }

    /**
     * 刷新 StatusBar
     */
//    private void refreshStatusBar() {
//        if (Build.VERSION.SDK_INT >= 21) {
//            TypedValue typedValue = new TypedValue();
//            Resources.Theme theme = getTheme();
//            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
//            getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
//        }
//    }

    /**
     * 展示一个切换动画
     */
    private void showAnimation() {
        final View decorView = getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(this);
            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParam);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
//
            objectAnimator.start();
        }
    }

    /**
     * 获取一个 View 的缓存视图
     *
     * @param view
     * @return
     */
    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }

}
