package com.anfly.weizixun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.weizixun.R;
import com.anfly.weizixun.bean.NaviBean;
import com.anfly.weizixun.ui.widget.FlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NaviAdapter extends RecyclerView.Adapter<NaviAdapter.ViewHolder> {
    private Context context;
    private ArrayList<NaviBean.DataBean> list;

    public NaviAdapter(Context context, ArrayList<NaviBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_navi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NaviBean.DataBean dataBean = list.get(position);
        for (int i = 0; i < dataBean.getArticles().size(); i++) {
            TextView view = (TextView) LayoutInflater.from(context).inflate(R.layout.item_navi_lable, null);
            view.setText(dataBean.getArticles().get(i).getTitle());
            holder.fl.addView(view);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fl)
        FlowLayout fl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
