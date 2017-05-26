package com.zxz.zhihudaliy.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxz.zhihudaliy.Acticity.MainActivity;

/**
 * Created by pc on 2017/5/14.
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment {
    protected Activity mActivity;

    public BaseFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        return initView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity = null;
    }
    protected abstract View initView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState);
    protected void initData() {

    }
    protected void hint(View view, String content, int color) {
        Snackbar snackbar = Snackbar.make(view, content, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(color);
        snackbar.show();
    }
    public MainActivity getRootActivity() {
        return (MainActivity) mActivity;
    }
}
