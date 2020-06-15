package com.anfly.weizixun.callback;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.bean.SpecialBean;

public interface SpecialCallBack extends BaseCallback<SpecialBean> {
    @Override
    void onSuccess(SpecialBean specialBean);

    @Override
    void onFail(String error);
}
