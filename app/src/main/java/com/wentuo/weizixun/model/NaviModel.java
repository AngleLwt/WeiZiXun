package com.wentuo.weizixun.model;

import com.wentuo.weizixun.base.BaseModel;
import com.wentuo.weizixun.bean.NaviBean;
import com.wentuo.weizixun.callback.NaviCallBack;
import com.wentuo.weizixun.net.ApiService;
import com.wentuo.weizixun.net.BaseObserver;
import com.wentuo.weizixun.net.HttpManager;
import com.wentuo.weizixun.net.RxUtil;

public class NaviModel extends BaseModel {
    public void getNaviData(NaviCallBack callBack) {
        HttpManager.getHttpManager()
                .getApiService(ApiService.baseWanAndroidUrl, ApiService.class)
                .getNaviData()
                .compose(RxUtil.rxFlowableTransformer())
                .subscribe(new BaseObserver<NaviBean>() {
                    @Override
                    public void onSuccess(NaviBean naviBean) {
                        callBack.onSuccess(naviBean);
                    }
                });
    }
}
