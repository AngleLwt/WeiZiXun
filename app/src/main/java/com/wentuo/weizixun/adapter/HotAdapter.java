package com.wentuo.weizixun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wentuo.weizixun.R;
import com.wentuo.weizixun.bean.HotBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HotBean.RecentBean> list;

    public HotAdapter(Context context, ArrayList<HotBean.RecentBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_daily_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HotBean.RecentBean recentBean = list.get(position);
        Glide.with(context).load(recentBean.getThumbnail()).into(holder.ivDaily);
        holder.tvTitle.setText(recentBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_daily)
        ImageView ivDaily;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
