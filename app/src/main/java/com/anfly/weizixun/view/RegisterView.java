package com.anfly.weizixun.view;

import com.anfly.weizixun.base.BaseView;
import com.anfly.weizixun.bean.LoginBean;
import com.anfly.weizixun.bean.RegisterBean;

public interface RegisterView extends BaseView<RegisterBean> {
    @Override
    void onSuccess(RegisterBean registerBean);

    @Override
    void onFail(String error);
}
