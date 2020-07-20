package com.wentuo.weizixun.view;

import com.wentuo.weizixun.base.BaseView;
import com.wentuo.weizixun.bean.LoginBean;

public interface LoginView extends BaseView<LoginBean> {
    @Override
    void onSuccess(LoginBean loginBean);

    @Override
    void onFail(String error);
}
