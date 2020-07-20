package com.wentuo.weizixun.view;

import com.wentuo.weizixun.base.BaseView;
import com.wentuo.weizixun.bean.DailyBean;

public interface DailyNewsView extends BaseView<DailyBean> {
    @Override
    void onSuccess(DailyBean dailyBean);

    @Override
    void onFail(String error);
}
