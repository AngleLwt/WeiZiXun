package com.wentuo.weizixun.ui.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.wentuo.weizixun.R;
import com.wentuo.weizixun.adapter.ZhihuVpAdapter;
import com.wentuo.weizixun.base.BaseActivity;
import com.wentuo.weizixun.ui.fragment.DailyNewsFragment;
import com.wentuo.weizixun.ui.fragment.HotFragment;
import com.wentuo.weizixun.ui.fragment.SpecilFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class ZhihuActitivty extends BaseActivity {

    @BindView(R.id.tab_zhihu)
    TabLayout tabZhihu;
    @BindView(R.id.vp_zhihu)
    ViewPager vpZhihu;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhihu;
    }

    @Override
    protected void initView() {
        super.initView();
        fragments = new ArrayList<>();
        fragments.add(new DailyNewsFragment());
        fragments.add(new SpecilFragment());
        fragments.add(new HotFragment());
        titles = new ArrayList<>();
        titles.add("日报");
        titles.add("专栏");
        titles.add("热门");

        ZhihuVpAdapter adapter = new ZhihuVpAdapter(getSupportFragmentManager(), fragments, titles);
        vpZhihu.setAdapter(adapter);
        tabZhihu.setupWithViewPager(vpZhihu);
    }
}
