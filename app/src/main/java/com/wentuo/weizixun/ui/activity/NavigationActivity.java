package com.wentuo.weizixun.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wentuo.weizixun.R;
import com.wentuo.weizixun.adapter.NaviAdapter;
import com.wentuo.weizixun.base.BaseMvpActivity;
import com.wentuo.weizixun.bean.NaviBean;
import com.wentuo.weizixun.presenter.NaviPresenter;
import com.wentuo.weizixun.view.NaviView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import qdx.stickyheaderdecoration.NormalDecoration;

public class NavigationActivity extends BaseMvpActivity<NaviPresenter, NaviView> implements NaviView {

    @BindView(R.id.rv_nv)
    RecyclerView rvNv;
    private ArrayList<NaviBean.DataBean> list;
    private NaviAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_navigation;
    }

    @Override
    protected NaviView initMvpView() {
        return this;
    }

    @Override
    protected NaviPresenter initMvpPresenter() {
        return new NaviPresenter();
    }

    @Override
    public void onSuccess(NaviBean naviBean) {
        List<NaviBean.DataBean> data = naviBean.getData();
        list.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        toast(error);
    }

    @Override
    protected void initView() {
        super.initView();
        rvNv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new NaviAdapter(this, list);

        NormalDecoration normalDecoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int i) {
                return list.get(i).getName();
            }
        };
        rvNv.addItemDecoration(normalDecoration);
        rvNv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getNaviDta();
    }
}
