package com.anfly.weizixun.ui.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.anfly.weizixun.R;
import com.anfly.weizixun.adapter.ZhihuVpAdapter;
import com.anfly.weizixun.base.BaseActivity;
import com.anfly.weizixun.ui.fragment.DailyNewsFragment;
import com.anfly.weizixun.ui.fragment.HotFragment;
import com.anfly.weizixun.ui.fragment.SpecilFragment;
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
