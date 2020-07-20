package com.wentuo.weizixun.model;

import com.wentuo.weizixun.base.BaseModel;
import com.wentuo.weizixun.bean.LoginBean;
import com.wentuo.weizixun.callback.LoginCallback;
import com.wentuo.weizixun.net.ApiService;
import com.wentuo.weizixun.net.BaseObserver;
import com.wentuo.weizixun.net.HttpManager;
import com.wentuo.weizixun.net.RxUtil;

public class LoginMdel extends BaseModel {
    public void login(String name, String pwd, LoginCallback callback) {
        HttpManager.getHttpManager().getApiService(ApiService.baseUrl, ApiService.class)
                .login(name, pwd)
                .compose(RxUtil.rxFlowableTransformer())
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        String errorCode = loginBean.getCode();
                        if (errorCode.equals("200")) {
                            callback.onSuccess(loginBean);
                        } else {
                            callback.onFail(loginBean.getMessage());
                        }
                    }
                });
    }
}