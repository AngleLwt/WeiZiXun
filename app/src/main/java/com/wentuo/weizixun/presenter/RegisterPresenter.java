package com.wentuo.weizixun.presenter;

import android.text.TextUtils;

import com.wentuo.weizixun.base.BasePresenter;
import com.wentuo.weizixun.bean.RegisterBean;
import com.wentuo.weizixun.callback.RegisterCallback;
import com.wentuo.weizixun.model.RegisterModel;

public class RegisterPresenter extends BasePresenter implements RegisterCallback {

    private RegisterModel model;

    @Override
    protected void initModel() {
        model = new RegisterModel();
        addModel(model);
    }

    public void register(String userid,
                         String psd,
                         String accessToken,
                         String typeid) {

        if (TextUtils.isEmpty(userid)) {
            mView.onFail("账号不能为空");
            return;
        }

        if (TextUtils.isEmpty(psd)) {
            mView.onFail("密码不能为空");
            return;
        }

        model.register(userid, psd, accessToken, typeid, this);
    }

    @Override
    public void onSuccess(RegisterBean registerBean) {
        mView.onSuccess(registerBean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
