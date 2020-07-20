package com.wentuo.weizixun.ui.activity;

import android.content.Intent;
import android.view.KeyEvent;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wentuo.weizixun.R;
import com.wentuo.weizixun.adapter.ItInfoTabUpdataAdapter;
import com.wentuo.weizixun.base.BaseActivity;
import com.wentuo.weizixun.bean.ITTabBean;
import com.wentuo.weizixun.callback.SimpleItemTouchHelperCallBack;
import com.wentuo.weizixun.common.Constants;

import java.util.ArrayList;

import butterknife.BindView;

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
