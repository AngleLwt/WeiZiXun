package com.wentuo.weizixun.callback;

import com.wentuo.weizixun.base.BaseCallback;
import com.wentuo.weizixun.bean.SpecialBean;

public interface SpecialCallBack extends BaseCallback<SpecialBean> {
    @Override
    void onSuccess(SpecialBean specialBean);

    @Override
    void onFail(String error);
}
