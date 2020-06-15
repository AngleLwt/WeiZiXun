package com.anfly.weizixun.view;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.base.BaseView;
import com.anfly.weizixun.bean.HotBean;

public interface HotView extends BaseView<HotBean> {
    @Override
    void onSuccess(HotBean hotBean);

    @Override
    void onFail(String error);
}
