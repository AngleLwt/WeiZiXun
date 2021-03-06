package com.wentuo.weizixun.ui.fragment;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wentuo.weizixun.R;
import com.wentuo.weizixun.adapter.SpecialAdapter;
import com.wentuo.weizixun.base.BaseMvpFragment;
import com.wentuo.weizixun.bean.SpecialBean;
import com.wentuo.weizixun.presenter.SpecialPresenter;
import com.wentuo.weizixun.view.SpecialView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecilFragment extends BaseMvpFragment<SpecialPresenter, SpecialView> implements SpecialView {

    @BindView(R.id.rv_special)
    RecyclerView rvSpecial;
    private ArrayList<SpecialBean.DataBean> list;
    private SpecialAdapter adapter;

    public SpecilFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_specil;
    }

    @Override
    protected SpecialView initMvpView() {
        return this;
    }

    @Override
    protected SpecialPresenter initMvpPresenter() {
        return new SpecialPresenter();
    }

    @Override
    public void onSuccess(SpecialBean specialBean) {
        list.addAll(specialBean.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initView() {
        super.initView();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        rvSpecial.setLayoutManager(manager);
        list = new ArrayList<>();

        adapter = new SpecialAdapter(getActivity(), list);
        rvSpecial.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getSpecialData();
    }
}
