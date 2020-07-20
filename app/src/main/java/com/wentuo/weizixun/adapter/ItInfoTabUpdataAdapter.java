package com.wentuo.weizixun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.wentuo.weizixun.R;
import com.wentuo.weizixun.bean.ITTabBean;
import com.wentuo.weizixun.callback.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItInfoTabUpdataAdapter extends RecyclerView.Adapter<ItInfoTabUpdataAdapter.ViewHolder> implements TouchCallBack {
    private Context context;
    private ArrayList<ITTabBean> list;

    public ItInfoTabUpdataAdapter(Context context, ArrayList<ITTabBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tab_choose, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ITTabBean itTabBean = list.get(position);
        holder.name.setText(itTabBean.getName());
        holder.sw.setChecked(itTabBean.isShow());

        holder.sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isPressed()) {
                    itTabBean.setShow(isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemMove(int fromPositon, int toPosition) {
        Collections.swap(list, fromPositon, toPosition);
        notifyItemMoved(fromPositon, toPosition);
    }

    @Override
    public void onItemDelete(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.sw)
        SwitchCompat sw;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
