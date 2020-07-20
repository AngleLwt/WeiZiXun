package com.wentuo.weizixun.presenter;

import android.text.TextUtils;

import com.wentuo.weizixun.base.BasePresenter;
import com.wentuo.weizixun.bean.LoginBean;
import com.wentuo.weizixun.callback.LoginCallback;
import com.wentuo.weizixun.model.LoginMdel;

public class LoginPresenter extends BasePresenter implements LoginCallback {

    private LoginMdel model;

    @Override
    protected void initModel() {
        model = new LoginMdel();
        addModel(model);
    }

    public void login(String name, String pws) {

        if (TextUtils.isEmpty(name)) {
            mView.onFail("账号不能为空");
            return;
        }

        if (TextUtils.isEmpty(pws)) {
            mView.onFail("密码不能为空");
            return;
        }

        model.login(name, pws, this);
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        mView.onSuccess(loginBean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
