package com.anfly.weizixun.presenter;

import android.text.TextUtils;

import com.anfly.weizixun.base.BasePresenter;
import com.anfly.weizixun.bean.LoginBean;
import com.anfly.weizixun.bean.RegisterBean;
import com.anfly.weizixun.callback.LoginCallback;
import com.anfly.weizixun.callback.RegisterCallback;
import com.anfly.weizixun.model.LoginMdel;
import com.anfly.weizixun.model.RegisterModel;

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
