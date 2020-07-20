package com.wentuo.weizixun.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.wentuo.weizixun.R;
import com.wentuo.weizixun.adapter.ITInfoVpAdapter;
import com.wentuo.weizixun.base.BaseMvpActivity;
import com.wentuo.weizixun.bean.ITTabBean;
import com.wentuo.weizixun.bean.ItInfoBean;
import com.wentuo.weizixun.common.Constants;
import com.wentuo.weizixun.presenter.ItInfoPresenter;
import com.wentuo.weizixun.ui.fragment.WxArticleListFragment;
import com.wentuo.weizixun.view.ItInfoView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ItInfoActivity extends BaseMvpActivity<ItInfoPresenter, ItInfoView> implements ItInfoView {

    @BindView(R.id.tab_it)
    TabLayout tabIt;
    @BindView(R.id.iv_it)
    ImageView ivIt;
    @BindView(R.id.cl_it)
    ConstraintLayout clIt;
    @BindView(R.id.vp_it)
    ViewPager vpIt;
    private ITInfoVpAdapter adapter;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private ArrayList<ITTabBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_it_info;
    }

    @Override
    protected ItInfoView initMvpView() {
        return this;
    }

    @Override
    protected ItInfoPresenter initMvpPresenter() {
        return new ItInfoPresenter();
    }

    @Override
    public void onSuccess(ItInfoBean itInfoBean) {
        List<ItInfoBean.DataBean> data = itInfoBean.getData();
        for (int i = 0; i < data.size(); i++) {
            ItInfoBean.DataBean dataBean = data.get(i);
            ITTabBean itTabBean = new ITTabBean(dataBean.getName(), dataBean.getId(), true);
            list.add(itTabBean);
        }
        refreshUI();
    }

    private void refreshUI() {
        for (int i = 0; i < list.size(); i++) {
            ITTabBean itTabBean = list.get(i);
            boolean show = itTabBean.isShow();
            if (show) {
                fragments.add(new WxArticleListFragment(itTabBean.getId()));
                titles.add(itTabBean.getName());
            }
        }
        adapter = new ITInfoVpAdapter(getSupportFragmentManager(), fragments, titles);
        vpIt.setAdapter(adapter);
        tabIt.setupWithViewPager(vpIt);
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(ItInfoActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getItInfoData();
    }

    @Override
    protected void initView() {
        super.initView();
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        list = new ArrayList<>();
    }

    @OnClick({R.id.iv_it, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_it:
                Intent intent = new Intent(ItInfoActivity.this, ItInfoTabUpdataActivity.class);
                intent.putExtra(Constants.LIST, list);
                startActivityForResult(intent, 100);
                break;
            case R.id.iv_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            list = (ArrayList<ITTabBean>) data.getSerializableExtra(Constants.LIST);
            fragments.clear();
            titles.clear();
            refreshUI();
        }
    }


}
