package com.anfly.weizixun.callback;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.bean.NaviBean;

public interface NaviCallBack extends BaseCallback<NaviBean> {
    @Override
    void onSuccess(NaviBean naviBean);

    @Override
    void onFail(String error);
}
