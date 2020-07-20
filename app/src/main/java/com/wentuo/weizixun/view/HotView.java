package com.wentuo.weizixun.view;

import com.wentuo.weizixun.base.BaseView;
import com.wentuo.weizixun.bean.HotBean;

public interface HotView extends BaseView<HotBean> {
    @Override
    void onSuccess(HotBean hotBean);

    @Override
    void onFail(String error);
}
