package com.anfly.weizixun.presenter;

import com.anfly.weizixun.base.BasePresenter;
import com.anfly.weizixun.bean.DailyNewsDetailsBean;
import com.anfly.weizixun.callback.DailyNewsDetailsCallBack;
import com.anfly.weizixun.model.DailyNewsDetailsModel;

public class DailyNewsDetailsPresenter extends BasePresenter implements DailyNewsDetailsCallBack {

    private DailyNewsDetailsModel model;

    @Override
    protected void initModel() {
        model = new DailyNewsDetailsModel();
        addModel(model);
    }

    public void getData(int id) {
        model.getDailyNewsDetailsData(id, this);
    }

    @Override
    public void onSuccess(DailyNewsDetailsBean dailyNewsDetailsBean) {
        mView.onSuccess(dailyNewsDetailsBean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
