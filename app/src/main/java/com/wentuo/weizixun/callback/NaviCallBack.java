package com.wentuo.weizixun.callback;

import com.wentuo.weizixun.base.BaseCallback;
import com.wentuo.weizixun.bean.NaviBean;

public interface NaviCallBack extends BaseCallback<NaviBean> {
    @Override
    void onSuccess(NaviBean naviBean);

    @Override
    void onFail(String error);
}
