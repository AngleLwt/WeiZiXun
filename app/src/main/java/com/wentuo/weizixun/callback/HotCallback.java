package com.wentuo.weizixun.callback;

import com.wentuo.weizixun.base.BaseCallback;
import com.wentuo.weizixun.bean.HotBean;

public interface HotCallback extends BaseCallback<HotBean> {
    @Override
    void onSuccess(HotBean hotBean);

    @Override
    void onFail(String error);
}
