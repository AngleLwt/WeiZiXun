package com.anfly.weizixun.callback;

import com.anfly.weizixun.base.BaseView;
import com.anfly.weizixun.bean.DailyBean;

public interface DailyNewsCallBack extends BaseView<DailyBean> {
    @Override
    void onSuccess(DailyBean dailyBean);

    @Override
    void onFail(String error);
}
