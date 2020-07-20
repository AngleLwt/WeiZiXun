package com.wentuo.weizixun.view;

import com.wentuo.weizixun.base.BaseView;
import com.wentuo.weizixun.bean.SpecialBean;

public interface SpecialView extends BaseView<SpecialBean> {
    @Override
    void onSuccess(SpecialBean specialBean);

    @Override
    void onFail(String error);
}
