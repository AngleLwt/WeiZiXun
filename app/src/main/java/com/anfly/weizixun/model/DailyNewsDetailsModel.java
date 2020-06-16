package com.anfly.weizixun.model;

import com.anfly.weizixun.base.BaseModel;
import com.anfly.weizixun.bean.DailyNewsDetailsBean;
import com.anfly.weizixun.callback.DailyNewsDetailsCallBack;
import com.anfly.weizixun.net.ApiService;
import com.anfly.weizixun.net.BaseObserver;
import com.anfly.weizixun.net.HttpManager;
import com.anfly.weizixun.net.RxUtil;

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
