package com.wentuo.weizixun.model;

import com.wentuo.weizixun.base.BaseModel;
import com.wentuo.weizixun.bean.DailyBean;
import com.wentuo.weizixun.callback.DailyNewsCallBack;
import com.wentuo.weizixun.net.ApiService;
import com.wentuo.weizixun.net.BaseObserver;
import com.wentuo.weizixun.net.HttpManager;
import com.wentuo.weizixun.net.RxUtil;

public class DailyNewsModel extends BaseModel {
    public void getDailyData(DailyNewsCallBack callBack) {
        HttpManager.getHttpManager().getApiService(ApiService.baseZhiUrl, ApiService.class)
                .getDailyData()
                .compose(RxUtil.rxFlowableTransformer())
                .subscribeWith(new BaseObserver<DailyBean>() {
                    @Override
                    public void onSuccess(DailyBean dailyBean) {
                        if (dailyBean != null) {
                            callBack.onSuccess(dailyBean);
                        } else {
                            callBack.onFail("数据为空");
                        }
                    }
                });
    }

    public void getDailyBeforeData(String date, DailyNewsCallBack callBack) {
        HttpManager.getHttpManager().getApiService(ApiService.baseZhiUrl, ApiService.class)
                .getBeforeData(date)
                .compose(RxUtil.rxFlowableTransformer())
                .subscribeWith(new BaseObserver<DailyBean>() {
                    @Override
                    public void onSuccess(DailyBean dailyBean) {
                        if (dailyBean != null) {
                            callBack.onSuccess(dailyBean);
                        } else {
                            callBack.onFail("数据为空");
                        }
                    }
                });
    }
}
