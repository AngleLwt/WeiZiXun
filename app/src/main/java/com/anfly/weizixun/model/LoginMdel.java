package com.anfly.weizixun.model;

import com.anfly.weizixun.base.BaseModel;
import com.anfly.weizixun.bean.LoginBean;
import com.anfly.weizixun.callback.LoginCallback;
import com.anfly.weizixun.net.ApiService;
import com.anfly.weizixun.net.BaseObserver;
import com.anfly.weizixun.net.HttpManager;
import com.anfly.weizixun.net.RxUtil;

public class LoginMdel extends BaseModel {
    public void login(String name, String pwd, LoginCallback callback) {
        HttpManager.getHttpManager().getApiService(ApiService.baseLoginUrl, ApiService.class)
                .login(name, pwd)
                .compose(RxUtil.rxFlowableTransformer())
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        int errorCode = loginBean.getErrorCode();
                        if (errorCode == 0) {
                            callback.onSuccess(loginBean);
                        } else {
                            callback.onFail(loginBean.getErrorMsg());
                        }
                    }
                });
    }
}