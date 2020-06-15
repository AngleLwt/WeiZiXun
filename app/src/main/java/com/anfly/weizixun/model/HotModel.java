package com.anfly.weizixun.model;

import com.anfly.weizixun.base.BaseModel;
import com.anfly.weizixun.bean.HotBean;
import com.anfly.weizixun.callback.HotCallback;
import com.anfly.weizixun.net.ApiService;
import com.anfly.weizixun.net.BaseObserver;
import com.anfly.weizixun.net.HttpManager;
import com.anfly.weizixun.net.RxUtil;

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
