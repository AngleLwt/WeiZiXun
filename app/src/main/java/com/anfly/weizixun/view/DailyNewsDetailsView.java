package com.anfly.weizixun.view;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.base.BaseView;
import com.anfly.weizixun.bean.DailyNewsDetailsBean;

public interface DailyNewsDetailsView extends BaseView<DailyNewsDetailsBean> {
    @Override
    void onSuccess(DailyNewsDetailsBean dailyNewsDetailsBean);

    @Override
    void onFail(String error);
}
