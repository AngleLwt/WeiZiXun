package com.wentuo.weizixun.callback;

import com.wentuo.weizixun.base.BaseCallback;
import com.wentuo.weizixun.bean.WxArticleBean;

public interface WxArticleListCallBack extends BaseCallback<WxArticleBean> {

    @Override
    void onSuccess(WxArticleBean wxArticleBean);

    @Override
    void onFail(String error);
}
