package com.anfly.weizixun.model;

import com.anfly.weizixun.base.BaseModel;
import com.anfly.weizixun.bean.SpecialBean;
import com.anfly.weizixun.callback.SpecialCallBack;
import com.anfly.weizixun.net.ApiService;
import com.anfly.weizixun.net.BaseObserver;
import com.anfly.weizixun.net.HttpManager;
import com.anfly.weizixun.net.RxUtil;

public class SpecialModel extends BaseModel {
    public void getSpecialData(SpecialCallBack specialCallBack) {
        HttpManager.getHttpManager()
                .getApiService(ApiService.baseZhiUrl, ApiService.class)
                .getSpecialData()
                .compose(RxUtil.rxFlowableTransformer())
                .subscribeWith(new BaseObserver<SpecialBean>() {
                    @Override
                    public void onSuccess(SpecialBean specialBean) {
                        if (specialBean != null) {
                            specialCallBack.onSuccess(specialBean);
                        } else {
                            specialCallBack.onFail("数据为空");
                        }
                    }
                });
    }
}
