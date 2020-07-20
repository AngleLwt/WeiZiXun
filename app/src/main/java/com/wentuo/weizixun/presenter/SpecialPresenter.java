package com.wentuo.weizixun.presenter;

import com.wentuo.weizixun.base.BasePresenter;
import com.wentuo.weizixun.bean.SpecialBean;
import com.wentuo.weizixun.callback.SpecialCallBack;
import com.wentuo.weizixun.model.SpecialModel;

public class SpecialPresenter extends BasePresenter implements SpecialCallBack {

    private SpecialModel model;

    @Override
    protected void initModel() {
        model = new SpecialModel();
    }

    public void getSpecialData() {
        model.getSpecialData(this);
    }

    @Override
    public void onSuccess(SpecialBean specialBean) {
        mView.onSuccess(specialBean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
