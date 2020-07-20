package com.wentuo.weizixun.model;

import com.wentuo.weizixun.base.BaseModel;
import com.wentuo.weizixun.bean.SpecialBean;
import com.wentuo.weizixun.callback.SpecialCallBack;
import com.wentuo.weizixun.net.ApiService;
import com.wentuo.weizixun.net.BaseObserver;
import com.wentuo.weizixun.net.HttpManager;
import com.wentuo.weizixun.net.RxUtil;

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
