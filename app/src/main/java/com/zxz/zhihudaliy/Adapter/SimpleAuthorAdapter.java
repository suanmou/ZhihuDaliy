package com.zxz.zhihudaliy.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxz.zhihudaliy.Acticity.AuthorActivity;
import com.zxz.zhihudaliy.Acticity.R;

/**
 * Created by suan on 2017/6/10.
 */

public class SimpleAuthorAdapter extends RecyclerView.Adapter<SimpleAuthorAdapter.SimpleViewHolder>{

    private final View.OnClickListener mSimpleClickListener = new View.OnClickListener() {

        public void onClick(View v) {
            Context context = v.getContext();
            Intent intent = new Intent(context, AuthorActivity.class);
            context.startActivity(intent);
        }
    };


    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.author_info_layout, parent, false);
        return new SimpleViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.itemView.setOnClickListener(mSimpleClickListener);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
