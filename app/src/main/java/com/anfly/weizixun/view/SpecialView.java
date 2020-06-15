package com.anfly.weizixun.view;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.base.BaseView;
import com.anfly.weizixun.bean.SpecialBean;

public interface SpecialView extends BaseView<SpecialBean> {
    @Override
    void onSuccess(SpecialBean specialBean);

    @Override
    void onFail(String error);
}
