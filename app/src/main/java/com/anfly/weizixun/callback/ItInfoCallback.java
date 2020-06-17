package com.anfly.weizixun.callback;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.bean.ItInfoBean;

public interface ItInfoCallback extends BaseCallback<ItInfoBean> {
    @Override
    void onSuccess(ItInfoBean itInfoBean);

    @Override
    void onFail(String error);
}
