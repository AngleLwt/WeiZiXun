package com.wentuo.weizixun.callback;

import com.wentuo.weizixun.base.BaseView;
import com.wentuo.weizixun.bean.DailyBean;

public interface DailyNewsCallBack extends BaseView<DailyBean> {
    @Override
    void onSuccess(DailyBean dailyBean);

    @Override
    void onFail(String error);
}
