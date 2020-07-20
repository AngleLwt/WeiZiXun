package com.wentuo.weizixun.callback;

import com.wentuo.weizixun.base.BaseCallback;
import com.wentuo.weizixun.bean.LoginBean;

public interface LoginCallback extends BaseCallback<LoginBean> {
    @Override
    void onSuccess(LoginBean loginBean);

    @Override
    void onFail(String error);
}
