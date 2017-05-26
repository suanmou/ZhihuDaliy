package com.zxz.zhihudaliy.Acticity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zxz.zhihudaliy.Acticity.R;

//import com.zxz.zhihudaily.Net.HttpUtil;



public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private SwipeRefreshLayout refreshLayout;

    //文章的发布日期
    private String date;

    //用来实现再按一次退出程序的效果
    private boolean isExit;

    private int currentId;

    public boolean isHomepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        currentId = -1;
//        getTransition().add(R.id.fl_content, new MainFragment(), "Fragment" + currentId).commit();
        isHomepage = true;

//        Intent intent = new Intent(this, StartActivity.class);
//        startActivity(intent);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                if (isHomepage) {
//                    MainFragment mainFragment = (MainFragment) getFragmentByTag("Fragment" + currentId);
//                    mainFragment.getLatestArticleList();
//                } else {
//                    ThemeFragment themeFragment = (ThemeFragment) getFragmentByTag("Fragment" + currentId);
//                    themeFragment.refreshData();
//                }
//            }
//        });
    }

//    //获取主页
//    public void getHomepage() {
//        MainFragment mainFragment = (MainFragment) getFragmentByTag("Fragment" + "-1");
//        ThemeFragment themeFragment = (ThemeFragment) getFragmentByTag("Fragment" + currentId);
//        FragmentTransaction transition = getTransition();
//        transition.hide(themeFragment);
//        if (mainFragment == null) {
//            transition.add(R.id.fl_content, new MainFragment(), "Fragment" + "-1").commit();
//        } else {
//            transition.show(mainFragment).commit();
//        }
//        currentId = -1;
//        isHomepage = true;
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle("享受阅读的乐趣");
//        }
//    }
//
//    //获取主题Fragment
//    public void getThemeFragment(int id, Bundle bundle) {
//        ThemeFragment toFragment = (ThemeFragment) getFragmentByTag("Fragment" + id);
//        BaseFragment nowFragment;
//        if (isHomepage) {
//            nowFragment = (MainFragment) getFragmentByTag("Fragment" + currentId);
//        } else {
//            nowFragment = (ThemeFragment) getFragmentByTag("Fragment" + currentId);
//        }
//        FragmentTransaction transition = getTransition();
//        transition.hide(nowFragment);
//        if (toFragment == null) {
//            ThemeFragment themeFragment = new ThemeFragment();
//            themeFragment.setArguments(bundle);
//            transition.add(R.id.fl_content, themeFragment, "Fragment" + id).commit();
//        } else {
//            transition.show(toFragment).commit();
//            if (getSupportActionBar() != null) {
//                getSupportActionBar().setTitle(bundle.getString("Title"));
//            }
//        }
//        currentId = id;
//        isHomepage = false;
//        setRefresh(false);
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            closeDrawerLayout();
//            return;
//        }
//        if (!isHomepage) {
//            getHomepage();
//            return;
//        }
//        if (isExit) {
//            refreshLayout.setRefreshing(false);
//            HttpUtil.client.cancelAllRequests(true);
//            super.onBackPressed();
//        } else {
//            hint();
//        }
//    }

    //    private void hint() {
//        Snackbar snackbar = Snackbar.make(refreshLayout, "再按一次退出", Snackbar.LENGTH_SHORT);
//        snackbar.getView().setBackgroundColor(Color.parseColor("#0099CC"));
//        snackbar.setCallback(new Snackbar.Callback() {
//            @Override
//            public void onDismissed(Snackbar snackbar, int event) {
//                isExit = false;
//            }
//
//            @Override
//            public void onShown(Snackbar snackbar) {
//                isExit = true;
//            }
//        }).show();
//    }
//
    private FragmentTransaction getTransition() {
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        return transition;
    }
//
//    public Fragment getFragmentByTag(String tag) {
//        return getSupportFragmentManager().findFragmentByTag(tag);
//    }

//    public int getCurrentId() {
//        return currentId;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public void setRefresh(boolean flag) {
//        refreshLayout.setRefreshing(flag);
//    }
//
//    public void closeDrawerLayout() {
//        this.drawerLayout.closeDrawer(GravityCompat.START);
//    }
//
//    public void joke(View view) {
//        Snackbar snackbar = Snackbar.make(refreshLayout, "其实并没有该功能，只是为了占个地方。。。", Snackbar.LENGTH_SHORT);
//        snackbar.getView().setBackgroundColor(Color.parseColor("#0099CC"));
//        snackbar.show();
//    }

}
