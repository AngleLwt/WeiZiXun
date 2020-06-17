package com.anfly.weizixun.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.weizixun.R;
import com.anfly.weizixun.adapter.ItTabChooseAdapter;
import com.anfly.weizixun.base.BaseActivity;
import com.anfly.weizixun.bean.ITTabBean;
import com.anfly.weizixun.callback.SimpleItemTouchHelperCallBack;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItTabChooseActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    private ArrayList<ITTabBean> list;
    private ItTabChooseAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_it_tab_choose;
    }

    @Override
    protected void initView() {
        super.initView();
        list = (ArrayList<ITTabBean>) getIntent().getSerializableExtra("list");
        rv.setLayoutManager(new LinearLayoutManager(this));

        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter = new ItTabChooseAdapter(this, list);

        rv.setAdapter(adapter);

        //为RecycleView添加ItemTouchHelper
        SimpleItemTouchHelperCallBack simpleItemTouchHelperCallBack = new SimpleItemTouchHelperCallBack(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchHelperCallBack);
        itemTouchHelper.attachToRecyclerView(rv);

    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("list", list);
        setResult(200, intent);
        super.onBackPressed();
    }

}
