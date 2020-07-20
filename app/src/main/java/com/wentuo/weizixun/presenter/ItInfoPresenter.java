package com.wentuo.weizixun.presenter;

import com.wentuo.weizixun.base.BasePresenter;
import com.wentuo.weizixun.bean.ItInfoBean;
import com.wentuo.weizixun.callback.ItInfoCallback;
import com.wentuo.weizixun.model.ItInfoModel;

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
