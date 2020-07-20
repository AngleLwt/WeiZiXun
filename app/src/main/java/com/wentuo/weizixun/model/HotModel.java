package com.wentuo.weizixun.model;

import com.wentuo.weizixun.base.BaseModel;
import com.wentuo.weizixun.bean.HotBean;
import com.wentuo.weizixun.callback.HotCallback;
import com.wentuo.weizixun.net.ApiService;
import com.wentuo.weizixun.net.BaseObserver;
import com.wentuo.weizixun.net.HttpManager;
import com.wentuo.weizixun.net.RxUtil;

public class HotModel extends BaseModel {
    public void getHotData(HotCallback hotCallback) {
        HttpManager.getHttpManager()
                .getApiService(ApiService.baseZhiUrl, ApiService.class)
                .getHotData()
                .compose(RxUtil.rxFlowableTransformer())
                .subscribeWith(new BaseObserver<HotBean>() {
                    @Override
                    public void onSuccess(HotBean hotBean) {
                        if (hotBean != null) {
                            hotCallback.onSuccess(hotBean);
                        } else {
                            hotCallback.onFail("数据为空");
                        }
                    }
                });
    }
}
