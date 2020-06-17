package com.anfly.weizixun.callback;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.bean.WxArticleBean;

public interface WxArticleListCallBack extends BaseCallback<WxArticleBean> {

    @Override
    void onSuccess(WxArticleBean wxArticleBean);

    @Override
    void onFail(String error);
}
