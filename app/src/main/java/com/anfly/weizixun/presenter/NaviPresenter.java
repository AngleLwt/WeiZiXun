package com.anfly.weizixun.presenter;

import com.anfly.weizixun.R;
import com.anfly.weizixun.base.BasePresenter;
import com.anfly.weizixun.bean.NaviBean;
import com.anfly.weizixun.callback.NaviCallBack;
import com.anfly.weizixun.model.NaviModel;

public class NaviPresenter extends BasePresenter implements NaviCallBack {

    private NaviModel model;

    @Override
    protected void initModel() {
        model = new NaviModel();
    }

    public void getNaviDta() {
        model.getNaviData(this);
    }

    @Override
    public void onSuccess(NaviBean naviBean) {
        mView.onSuccess(naviBean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
