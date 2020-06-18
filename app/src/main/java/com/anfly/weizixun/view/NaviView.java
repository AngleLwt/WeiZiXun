package com.anfly.weizixun.view;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.base.BaseView;
import com.anfly.weizixun.bean.NaviBean;

public interface NaviView extends BaseView<NaviBean> {
    @Override
    void onSuccess(NaviBean naviBean);

    @Override
    void onFail(String error);
}
