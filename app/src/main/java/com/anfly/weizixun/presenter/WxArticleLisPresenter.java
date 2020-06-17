package com.anfly.weizixun.presenter;

import com.anfly.weizixun.base.BasePresenter;
import com.anfly.weizixun.bean.WxArticleBean;
import com.anfly.weizixun.callback.WxArticleListCallBack;
import com.anfly.weizixun.model.WxArticleListModel;

public class WxArticleLisPresenter extends BasePresenter implements WxArticleListCallBack {

    private WxArticleListModel model;

    @Override
    protected void initModel() {
        model = new WxArticleListModel();
    }

    public void getWxArticleLisData(int id, int page) {
        model.getWxArticleData(id, page, this);
    }

    @Override
    public void onSuccess(WxArticleBean wxArticleBean) {
        mView.onSuccess(wxArticleBean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
