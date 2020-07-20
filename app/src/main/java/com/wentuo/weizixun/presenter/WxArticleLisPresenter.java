package com.wentuo.weizixun.presenter;

import com.wentuo.weizixun.base.BasePresenter;
import com.wentuo.weizixun.bean.WxArticleBean;
import com.wentuo.weizixun.callback.WxArticleListCallBack;
import com.wentuo.weizixun.model.WxArticleListModel;

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
