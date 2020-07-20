package com.wentuo.weizixun.presenter;

import com.wentuo.weizixun.base.BasePresenter;
import com.wentuo.weizixun.bean.DailyBean;
import com.wentuo.weizixun.callback.DailyNewsCallBack;
import com.wentuo.weizixun.model.DailyNewsModel;

public class DailyNewsPresenter extends BasePresenter implements DailyNewsCallBack {

    private DailyNewsModel model;

    @Override
    protected void initModel() {
        model = new DailyNewsModel();
        addModel(model);
    }

    public void getDailyData() {
        model.getDailyData(this);
    }

    public void getDailyBeforeData(String date) {
        model.getDailyBeforeData(date, this);
    }

    @Override
    public void onSuccess(DailyBean dailyBean) {
        mView.onSuccess(dailyBean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
