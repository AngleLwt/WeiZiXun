package com.wentuo.weizixun.view;

import com.wentuo.weizixun.base.BaseView;
import com.wentuo.weizixun.bean.DailyNewsDetailsBean;

public interface DailyNewsDetailsView extends BaseView<DailyNewsDetailsBean> {
    @Override
    void onSuccess(DailyNewsDetailsBean dailyNewsDetailsBean);

    @Override
    void onFail(String error);
}
