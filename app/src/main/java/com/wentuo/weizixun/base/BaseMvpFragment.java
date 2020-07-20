package com.wentuo.weizixun.base;

public abstract class BaseMvpFragment<P extends BasePresenter, V extends BaseView> extends BaseFragment {
    protected P mPresenter;

    @Override
    public void initMvp() {
        super.initMvp();

        mPresenter = initMvpPresenter();
        if (mPresenter != null) {
            mPresenter.setmView(initMvpView());
        }
    }

    protected abstract V initMvpView();

    protected abstract P initMvpPresenter();

}
