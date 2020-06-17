package com.anfly.weizixun.presenter;

import com.anfly.weizixun.base.BasePresenter;
import com.anfly.weizixun.bean.ItInfoBean;
import com.anfly.weizixun.callback.ItInfoCallback;
import com.anfly.weizixun.model.ItInfoModel;

public class ItInfoPresenter extends BasePresenter implements ItInfoCallback {

    private ItInfoModel model;

    @Override
    protected void initModel() {
        model = new ItInfoModel();
        addModel(model);
    }

    public void getItInfoData() {
        model.getItInfoData(this);
    }

    @Override
    public void onSuccess(ItInfoBean itInfoBean) {
        mView.onSuccess(itInfoBean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
