package com.wentuo.weizixun.ui.fragment;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wentuo.weizixun.R;
import com.wentuo.weizixun.adapter.HotAdapter;
import com.wentuo.weizixun.base.BaseMvpFragment;
import com.wentuo.weizixun.bean.HotBean;
import com.wentuo.weizixun.presenter.HotPresenter;
import com.wentuo.weizixun.view.HotView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseMvpFragment<HotPresenter, HotView> implements HotView {

    @BindView(R.id.rv_hot)
    RecyclerView rvHot;
    private ArrayList<HotBean.RecentBean> list;
    private HotAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected HotView initMvpView() {
        return this;
    }

    @Override
    protected HotPresenter initMvpPresenter() {
        return new HotPresenter();
    }

    @Override
    public void onSuccess(HotBean hotBean) {
        list.addAll(hotBean.getRecent());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getHotData();
    }

    @Override
    public void initView() {
        super.initView();
        rvHot.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new HotAdapter(getActivity(), list);
        rvHot.setAdapter(adapter);
    }
}
