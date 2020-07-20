package com.wentuo.weizixun.presenter;

import com.wentuo.weizixun.base.BasePresenter;
import com.wentuo.weizixun.bean.HotBean;
import com.wentuo.weizixun.callback.HotCallback;
import com.wentuo.weizixun.model.HotModel;

public class HotPresenter extends BasePresenter implements HotCallback {

    private HotModel model;

    @Override
    protected void initModel() {
        model = new HotModel();
    }

    public void getHotData() {
        model.getHotData(this);
    }

    @Override
    public void onSuccess(HotBean hotBean) {
        mView.onSuccess(hotBean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
