package com.anfly.weizixun.model;

import com.anfly.weizixun.base.BaseModel;
import com.anfly.weizixun.bean.RegisterBean;
import com.anfly.weizixun.callback.RegisterCallback;
import com.anfly.weizixun.net.ApiService;
import com.anfly.weizixun.net.BaseObserver;
import com.anfly.weizixun.net.HttpManager;
import com.anfly.weizixun.net.RxUtil;

public class RegisterModel extends BaseModel {
    public void register(String userid,
                         String psd,
                         String accessToken,
                         String typeid,
                         RegisterCallback callback) {
        HttpManager.getHttpManager().getApiService(ApiService.baseUrl, ApiService.class)
                .register(userid, psd, accessToken, typeid)
                .compose(RxUtil.rxFlowableTransformer())
                .subscribe(new BaseObserver<RegisterBean>() {
                    @Override
                    public void onSuccess(RegisterBean registerBean) {
                        callback.onSuccess(registerBean);
                    }
                });
    }
}
