package com.wentuo.weizixun.ui.fragment;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wentuo.weizixun.R;
import com.wentuo.weizixun.adapter.DailyAdapter;
import com.wentuo.weizixun.base.BaseMvpFragment;
import com.wentuo.weizixun.bean.DailyBean;
import com.wentuo.weizixun.common.Constants;
import com.wentuo.weizixun.presenter.DailyNewsPresenter;
import com.wentuo.weizixun.ui.activity.CalenderActivity;
import com.wentuo.weizixun.ui.activity.DailyNewsDetailsActivity;
import com.wentuo.weizixun.view.DailyNewsView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

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
        if (list != null) {
            list.clear();
        }
        if (banners != null) {
            banners.clear();
        }
        List<DailyBean.StoriesBean> stories = dailyBean.getStories();
        if (stories != null && stories.size() > 0) {
            list.addAll(stories);
        }
        List<DailyBean.TopStoriesBean> top_stories = dailyBean.getTop_stories();
        if (top_stories != null && top_stories.size() > 0) {
            banners.addAll(top_stories);
        }
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
        adapter.setOnItemClickListener(new DailyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DailyBean.StoriesBean storiesBean = list.get(position);
                Intent intent = new Intent(getActivity(), DailyNewsDetailsActivity.class);
                intent.putExtra(Constants.ID, storiesBean.getId());
                intent.putExtra(Constants.TITLE, storiesBean.getTitle());
                intent.putExtra(Constants.IMAGE, storiesBean.getImages().get(0));

                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getDailyData();
    }

    @OnClick(R.id.fab_daily_news)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), CalenderActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            String date = data.getStringExtra("date");
            mPresenter.getDailyBeforeData(date);
        }
    }
}
