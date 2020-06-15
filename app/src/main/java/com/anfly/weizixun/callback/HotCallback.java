package com.anfly.weizixun.callback;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.bean.HotBean;

public interface HotCallback extends BaseCallback<HotBean> {
    @Override
    void onSuccess(HotBean hotBean);

    @Override
    void onFail(String error);
}
