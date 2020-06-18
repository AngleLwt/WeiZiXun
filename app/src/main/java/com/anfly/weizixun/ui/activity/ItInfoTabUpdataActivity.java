package com.anfly.weizixun.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.weizixun.R;
import com.anfly.weizixun.adapter.ItInfoTabUpdataAdapter;
import com.anfly.weizixun.base.BaseActivity;
import com.anfly.weizixun.bean.ITTabBean;
import com.anfly.weizixun.callback.SimpleItemTouchHelperCallBack;
import com.anfly.weizixun.common.Constants;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItInfoTabUpdataActivity extends BaseActivity {

    @BindView(R.id.rv_info_tab_updata)
    RecyclerView rvInfoTabUpdata;
    private ArrayList<ITTabBean> list;
    private ItInfoTabUpdataAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_it_info_tab_updata;
    }

    @Override
    protected void initView() {
        super.initView();
        rvInfoTabUpdata.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new ItInfoTabUpdataAdapter(this, list);
        rvInfoTabUpdata.setAdapter(adapter);

        //里式替换
        SimpleItemTouchHelperCallBack simpleItemTouchHelperCallBack = new SimpleItemTouchHelperCallBack(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchHelperCallBack);
        itemTouchHelper.attachToRecyclerView(rvInfoTabUpdata);

    }

    @Override
    protected void initData() {
        super.initData();
        ArrayList<ITTabBean> datas = (ArrayList<ITTabBean>) getIntent().getSerializableExtra(Constants.LIST);
        list.addAll(datas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra(Constants.LIST, list);
            setResult(200, intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
