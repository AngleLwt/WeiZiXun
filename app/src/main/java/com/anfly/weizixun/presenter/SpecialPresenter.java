package com.anfly.weizixun.presenter;

import com.anfly.weizixun.base.BasePresenter;
import com.anfly.weizixun.bean.SpecialBean;
import com.anfly.weizixun.callback.SpecialCallBack;
import com.anfly.weizixun.model.SpecialModel;

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
