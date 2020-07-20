package com.wentuo.weizixun.presenter;

import com.wentuo.weizixun.base.BasePresenter;
import com.wentuo.weizixun.bean.DailyNewsDetailsBean;
import com.wentuo.weizixun.callback.DailyNewsDetailsCallBack;
import com.wentuo.weizixun.model.DailyNewsDetailsModel;

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
