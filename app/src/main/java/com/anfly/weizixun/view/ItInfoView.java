package com.anfly.weizixun.view;

import com.anfly.weizixun.base.BaseView;
import com.anfly.weizixun.bean.ItInfoBean;

public interface ItInfoView extends BaseView<ItInfoBean> {
    @Override
    void onSuccess(ItInfoBean itInfoBean);

    @Override
    void onFail(String error);
}
