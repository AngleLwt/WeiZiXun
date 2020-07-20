package com.wentuo.weizixun.model;

import com.wentuo.weizixun.base.BaseModel;
import com.wentuo.weizixun.bean.ItInfoBean;
import com.wentuo.weizixun.callback.ItInfoCallback;
import com.wentuo.weizixun.net.ApiService;
import com.wentuo.weizixun.net.BaseObserver;
import com.wentuo.weizixun.net.HttpManager;
import com.wentuo.weizixun.net.RxUtil;

public class ItInfoModel extends BaseModel {
    public void getItInfoData(ItInfoCallback callback) {
        HttpManager.getHttpManager()
                .getApiService(ApiService.baseWanAndroidUrl, ApiService.class)
                .getItInfoData()
                .compose(RxUtil.rxFlowableTransformer())
                .subscribe(new BaseObserver<ItInfoBean>() {
                    @Override
                    public void onSuccess(ItInfoBean itInfoBean) {
                        callback.onSuccess(itInfoBean);
                    }
                });
    }
}
