package com.anfly.weizixun.view;

import com.anfly.weizixun.base.BaseView;
import com.anfly.weizixun.bean.LoginBean;

public interface LoginView extends BaseView<LoginBean> {
    @Override
    void onSuccess(LoginBean loginBean);

    @Override
    void onFail(String error);
}
