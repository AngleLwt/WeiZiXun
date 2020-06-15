package com.anfly.weizixun.presenter;

import com.anfly.weizixun.base.BasePresenter;
import com.anfly.weizixun.bean.HotBean;
import com.anfly.weizixun.callback.HotCallback;
import com.anfly.weizixun.model.HotModel;

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
