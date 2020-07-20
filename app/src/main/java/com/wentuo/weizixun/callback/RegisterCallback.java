package com.wentuo.weizixun.callback;

import com.wentuo.weizixun.base.BaseCallback;
import com.wentuo.weizixun.bean.RegisterBean;

public interface RegisterCallback extends BaseCallback<RegisterBean> {
    @Override
    void onSuccess(RegisterBean registerBean);

    @Override
    void onFail(String error);
}
