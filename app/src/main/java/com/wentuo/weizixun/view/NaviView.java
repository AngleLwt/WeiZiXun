package com.wentuo.weizixun.view;

import com.wentuo.weizixun.base.BaseView;
import com.wentuo.weizixun.bean.NaviBean;

public interface NaviView extends BaseView<NaviBean> {
    @Override
    void onSuccess(NaviBean naviBean);

    @Override
    void onFail(String error);
}
