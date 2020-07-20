package com.wentuo.weizixun.model;

import com.wentuo.weizixun.base.BaseModel;
import com.wentuo.weizixun.bean.DailyNewsDetailsBean;
import com.wentuo.weizixun.callback.DailyNewsDetailsCallBack;
import com.wentuo.weizixun.net.ApiService;
import com.wentuo.weizixun.net.BaseObserver;
import com.wentuo.weizixun.net.HttpManager;
import com.wentuo.weizixun.net.RxUtil;

public class DailyNewsDetailsModel extends BaseModel {
    public void getDailyNewsDetailsData(int id, DailyNewsDetailsCallBack callBack) {
        HttpManager.getHttpManager()
                .getApiService(ApiService.baseZhiUrl, ApiService.class)
                .getDailyNewsDetalisData(id)
                .compose(RxUtil.rxFlowableTransformer())
                .subscribeWith(new BaseObserver<DailyNewsDetailsBean>() {
                    @Override
                    public void onSuccess(DailyNewsDetailsBean dailyNewsDetailsBean) {
                        if (dailyNewsDetailsBean != null) {
                            callBack.onSuccess(dailyNewsDetailsBean);
                        } else {
                            callBack.onFail("数据为空");
                        }
                    }
                });

    }
}
