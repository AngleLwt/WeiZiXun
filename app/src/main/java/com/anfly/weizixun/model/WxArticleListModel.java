package com.anfly.weizixun.model;

import com.anfly.weizixun.base.BaseModel;
import com.anfly.weizixun.bean.WxArticleBean;
import com.anfly.weizixun.callback.WxArticleListCallBack;
import com.anfly.weizixun.net.ApiService;
import com.anfly.weizixun.net.BaseObserver;
import com.anfly.weizixun.net.HttpManager;
import com.anfly.weizixun.net.RxUtil;

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
