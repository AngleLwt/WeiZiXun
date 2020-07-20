package com.wentuo.weizixun.view;

import com.wentuo.weizixun.base.BaseView;
import com.wentuo.weizixun.bean.WxArticleBean;

public interface WxArticleListView extends BaseView<WxArticleBean> {

    @Override
    void onSuccess(WxArticleBean wxArticleBean);

    @Override
    void onFail(String error);
}
