package com.anfly.weizixun.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anfly.weizixun.common.WzxApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter, V extends BaseView> extends AppCompatActivity {
    protected P mPresenter;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);

        mPresenter = initMvpPresenter();
        if (mPresenter != null) {
            mPresenter.setmView(initMvpView());
        }

        initView();
        initData();
        initListener();
    }

    protected abstract V initMvpView();

    protected abstract P initMvpPresenter();

    private void initListener() {

    }

    private void initData() {

    }

    private void initView() {

    }

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        mPresenter.onDestroy();
    }

    public void toast(String msg) {
        Toast.makeText(WzxApplication.getApplication(), msg, Toast.LENGTH_SHORT).show();
    }
}
