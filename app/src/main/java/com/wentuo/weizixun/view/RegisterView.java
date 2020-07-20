package com.wentuo.weizixun.view;

import com.wentuo.weizixun.base.BaseView;
import com.wentuo.weizixun.bean.RegisterBean;

public interface RegisterView extends BaseView<RegisterBean> {
    @Override
    void onSuccess(RegisterBean registerBean);

    @Override
    void onFail(String error);
}
