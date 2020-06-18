package com.anfly.weizixun.ui.activity;

import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.weizixun.R;
import com.anfly.weizixun.adapter.SearchAdapter;
import com.anfly.weizixun.adapter.WxArticleListAdapter;
import com.anfly.weizixun.base.BaseMvpActivity;
import com.anfly.weizixun.bean.SearchBean;
import com.anfly.weizixun.bean.WxArticleBean;
import com.anfly.weizixun.presenter.SearchPresenter;
import com.anfly.weizixun.view.SearchView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseMvpActivity<SearchPresenter, SearchView> implements SearchView {

    @BindView(R.id.toolbar_search)
    Toolbar toolbarSearch;
    @BindView(R.id.msv)
    MaterialSearchView msv;
    @BindView(R.id.fl_search)
    FrameLayout flSearch;
    @BindView(R.id.rv_search)
    RecyclerView rvSearch;
    private ArrayList<SearchBean.DataBean.DatasBean> list;
    private SearchAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchView initMvpView() {
        return this;
    }

    @Override
    protected SearchPresenter initMvpPresenter() {
        return new SearchPresenter();
    }

    @Override
    public void onSuccess(SearchBean searchBean) {
        List<SearchBean.DataBean.DatasBean> datas = searchBean.getData().getDatas();
        if (list.size() > 0) {
            list.clear();
            adapter.notifyDataSetChanged();
        }
        if (datas.size() <= 0) {
            toast("搜索为空");
            return;
        }
        list.addAll(datas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        toast(error);
    }

    @Override
    protected void initView() {
        super.initView();

        toolbarSearch.setTitle("搜索");
        setSupportActionBar(toolbarSearch);

        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new SearchAdapter(this, list);
        rvSearch.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_search_menu, menu);
        MenuItem action_search = menu.findItem(R.id.action_search);
//        action_search.setVisible(false);
        msv.setMenuItem(action_search);
        return true;
    }

    @Override
    protected void initListener() {
        super.initListener();
        msv.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.getSearchData(0, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
