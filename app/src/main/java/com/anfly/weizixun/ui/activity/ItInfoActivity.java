package com.anfly.weizixun.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.anfly.weizixun.R;
import com.anfly.weizixun.adapter.ITInfoVpAdapter;
import com.anfly.weizixun.base.BaseMvpActivity;
import com.anfly.weizixun.bean.ItInfoBean;
import com.anfly.weizixun.presenter.ItInfoPresenter;
import com.anfly.weizixun.ui.fragment.WxArticleListFragment;
import com.anfly.weizixun.view.ItInfoView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
            fragments.add(new WxArticleListFragment(data.get(i).getId()));
            titles.add(data.get(i).getName());
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
    }
}
