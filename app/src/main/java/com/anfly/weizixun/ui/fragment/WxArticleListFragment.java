package com.anfly.weizixun.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.weizixun.R;
import com.anfly.weizixun.adapter.WxArticleListAdapter;
import com.anfly.weizixun.base.BaseMvpFragment;
import com.anfly.weizixun.bean.WxArticleBean;
import com.anfly.weizixun.presenter.WxArticleLisPresenter;
import com.anfly.weizixun.view.WxArticleListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WxArticleListFragment extends BaseMvpFragment<WxArticleLisPresenter, WxArticleListView> implements WxArticleListView {

    @BindView(R.id.rv_wx_article)
    RecyclerView rvWxArticle;
    private int id;
    private int page = 0;
    private ArrayList<WxArticleBean.DataBean.DatasBean> list;
    private WxArticleListAdapter adapter;

    public WxArticleListFragment(int id) {
        this.id = id;
    }

    public WxArticleListFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wx_article_list;
    }

    @Override
    protected WxArticleListView initMvpView() {
        return this;
    }

    @Override
    protected WxArticleLisPresenter initMvpPresenter() {
        return new WxArticleLisPresenter();
    }

    @Override
    public void onSuccess(WxArticleBean wxArticleBean) {
        List<WxArticleBean.DataBean.DatasBean> datas = wxArticleBean.getData().getDatas();
        list.addAll(datas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initView() {
        super.initView();
        rvWxArticle.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new WxArticleListAdapter(getActivity(), list);
        rvWxArticle.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getWxArticleLisData(id, page);
    }
}
