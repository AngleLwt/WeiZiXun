package com.wentuo.weizixun.view;

import com.wentuo.weizixun.base.BaseView;
import com.wentuo.weizixun.bean.ItInfoBean;

public interface ItInfoView extends BaseView<ItInfoBean> {
    @Override
    void onSuccess(ItInfoBean itInfoBean);

    @Override
    void onFail(String error);
}
