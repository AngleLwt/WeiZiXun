package com.wentuo.weizixun.presenter;

import com.wentuo.weizixun.base.BasePresenter;
import com.wentuo.weizixun.bean.NaviBean;
import com.wentuo.weizixun.callback.NaviCallBack;
import com.wentuo.weizixun.model.NaviModel;

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
