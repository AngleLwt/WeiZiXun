package com.wentuo.weizixun.model;

import com.wentuo.weizixun.base.BaseModel;
import com.wentuo.weizixun.bean.WxArticleBean;
import com.wentuo.weizixun.callback.WxArticleListCallBack;
import com.wentuo.weizixun.net.ApiService;
import com.wentuo.weizixun.net.BaseObserver;
import com.wentuo.weizixun.net.HttpManager;
import com.wentuo.weizixun.net.RxUtil;

public class WxArticleListModel extends BaseModel {
    public void getWxArticleData(int id, int page, WxArticleListCallBack callBack) {
        HttpManager.getHttpManager()
                .getApiService(ApiService.baseWanAndroidUrl, ApiService.class)
                .getWxArticleData(id, page)
                .compose(RxUtil.rxFlowableTransformer())
                .subscribe(new BaseObserver<WxArticleBean>() {
                    @Override
                    public void onSuccess(WxArticleBean wxArticleBean) {
                        callBack.onSuccess(wxArticleBean);
                    }
                });
    }
}
