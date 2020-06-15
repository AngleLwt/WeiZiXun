package com.anfly.weizixun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.weizixun.R;
import com.anfly.weizixun.bean.SpecialBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SpecialBean.DataBean> list;

    public SpecialAdapter(Context context, ArrayList<SpecialBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_special, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpecialBean.DataBean dataBean = list.get(position);
        Glide.with(context).load(dataBean.getThumbnail()).into(holder.ivSpecial);
        holder.tvSpecial.setText(dataBean.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_special)
        ImageView ivSpecial;
        @BindView(R.id.tv_special)
        TextView tvSpecial;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
