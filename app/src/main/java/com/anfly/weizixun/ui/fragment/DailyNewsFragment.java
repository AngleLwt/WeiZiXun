package com.anfly.weizixun.ui.fragment;

import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.weizixun.R;
import com.anfly.weizixun.adapter.DailyAdapter;
import com.anfly.weizixun.base.BaseMvpFragment;
import com.anfly.weizixun.bean.DailyBean;
import com.anfly.weizixun.presenter.DailyNewsPresenter;
import com.anfly.weizixun.ui.activity.CalenderActivity;
import com.anfly.weizixun.view.DailyNewsView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyNewsFragment extends BaseMvpFragment<DailyNewsPresenter, DailyNewsView> implements DailyNewsView {

    @BindView(R.id.rv_daily_news)
    RecyclerView rvDailyNews;
    @BindView(R.id.fab_daily_news)
    FloatingActionButton fabDailyNews;
    private ArrayList<DailyBean.StoriesBean> list;
    private ArrayList<DailyBean.TopStoriesBean> banners;
    private DailyAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_news;
    }

    @Override
    protected DailyNewsView initMvpView() {
        return this;
    }

    @Override
    protected DailyNewsPresenter initMvpPresenter() {
        return new DailyNewsPresenter();
    }

    @Override
    public void onSuccess(DailyBean dailyBean) {
        list.addAll(dailyBean.getStories());
        banners.addAll(dailyBean.getTop_stories());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initView() {
        super.initView();
        rvDailyNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        banners = new ArrayList<>();
        adapter = new DailyAdapter(getActivity(), list, banners);
        rvDailyNews.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getDailyData();
    }

    @OnClick(R.id.fab_daily_news)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), CalenderActivity.class);
        startActivity(intent);
    }
}
