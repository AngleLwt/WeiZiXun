package com.anfly.weizixun.presenter;

import com.anfly.weizixun.base.BasePresenter;
import com.anfly.weizixun.bean.DailyBean;
import com.anfly.weizixun.callback.DailyNewsCallBack;
import com.anfly.weizixun.model.DailyNewsModel;
import com.anfly.weizixun.view.DailyNewsView;

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
