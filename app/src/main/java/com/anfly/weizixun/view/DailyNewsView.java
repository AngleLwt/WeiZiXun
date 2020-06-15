package com.anfly.weizixun.view;

import com.anfly.weizixun.base.BaseView;
import com.anfly.weizixun.bean.DailyBean;

public interface DailyNewsView extends BaseView<DailyBean> {
    @Override
    void onSuccess(DailyBean dailyBean);

    @Override
    void onFail(String error);
}
