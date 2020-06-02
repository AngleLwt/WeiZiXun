package com.anfly.weizixun.callback;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.bean.LoginBean;

public interface LoginCallback extends BaseCallback<LoginBean> {
    @Override
    void onSuccess(LoginBean loginBean);

    @Override
    void onFail(String error);
}
