package com.anfly.weizixun.model;

import com.anfly.weizixun.base.BaseModel;
import com.anfly.weizixun.bean.ItInfoBean;
import com.anfly.weizixun.callback.ItInfoCallback;
import com.anfly.weizixun.net.ApiService;
import com.anfly.weizixun.net.BaseObserver;
import com.anfly.weizixun.net.HttpManager;
import com.anfly.weizixun.net.RxUtil;

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
