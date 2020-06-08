package com.anfly.weizixun.callback;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.bean.LoginBean;
import com.anfly.weizixun.bean.RegisterBean;

public interface RegisterCallback extends BaseCallback<RegisterBean> {
    @Override
    void onSuccess(RegisterBean registerBean);

    @Override
    void onFail(String error);
}
