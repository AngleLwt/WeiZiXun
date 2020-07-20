package com.wentuo.weizixun.model;

import com.wentuo.weizixun.base.BaseModel;
import com.wentuo.weizixun.bean.RegisterBean;
import com.wentuo.weizixun.callback.RegisterCallback;
import com.wentuo.weizixun.net.ApiService;
import com.wentuo.weizixun.net.BaseObserver;
import com.wentuo.weizixun.net.HttpManager;
import com.wentuo.weizixun.net.RxUtil;

public class RegisterModel extends BaseModel {
    public void register(String userid,
                         String psd,
                         String accessToken,
                         String typeid,
                         RegisterCallback callback) {
        HttpManager.getHttpManager().getApiService(ApiService.baseUrl, ApiService.class)
                .register(userid, psd, "", "")
                .compose(RxUtil.rxFlowableTransformer())
                .subscribe(new BaseObserver<RegisterBean>() {
                    @Override
                    public void onSuccess(RegisterBean registerBean) {
                        callback.onSuccess(registerBean);
                    }
                });
    }
}
