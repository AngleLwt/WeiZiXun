package com.anfly.weizixun.model;

import com.anfly.weizixun.base.BaseModel;
import com.anfly.weizixun.bean.DailyBean;
import com.anfly.weizixun.callback.DailyNewsCallBack;
import com.anfly.weizixun.net.ApiService;
import com.anfly.weizixun.net.BaseObserver;
import com.anfly.weizixun.net.HttpManager;
import com.anfly.weizixun.net.RxUtil;
import com.anfly.weizixun.ui.fragment.HotFragment;

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
