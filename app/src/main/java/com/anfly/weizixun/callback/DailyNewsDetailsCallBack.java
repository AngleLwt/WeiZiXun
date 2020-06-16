package com.anfly.weizixun.callback;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.bean.DailyNewsDetailsBean;

public interface DailyNewsDetailsCallBack extends BaseCallback<DailyNewsDetailsBean> {
    @Override
    void onSuccess(DailyNewsDetailsBean dailyNewsDetailsBean);

    @Override
    void onFail(String error);
}
