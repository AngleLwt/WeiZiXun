package com.wentuo.weizixun.callback;

import com.wentuo.weizixun.base.BaseCallback;
import com.wentuo.weizixun.bean.ItInfoBean;

public interface ItInfoCallback extends BaseCallback<ItInfoBean> {
    @Override
    void onSuccess(ItInfoBean itInfoBean);

    @Override
    void onFail(String error);
}
