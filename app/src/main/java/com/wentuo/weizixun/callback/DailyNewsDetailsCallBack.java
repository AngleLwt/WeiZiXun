package com.wentuo.weizixun.callback;

import com.wentuo.weizixun.base.BaseCallback;
import com.wentuo.weizixun.bean.DailyNewsDetailsBean;

public interface DailyNewsDetailsCallBack extends BaseCallback<DailyNewsDetailsBean> {
    @Override
    void onSuccess(DailyNewsDetailsBean dailyNewsDetailsBean);

    @Override
    void onFail(String error);
}
