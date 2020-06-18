package com.anfly.weizixun.model;

import com.anfly.weizixun.base.BaseModel;
import com.anfly.weizixun.bean.NaviBean;
import com.anfly.weizixun.callback.NaviCallBack;
import com.anfly.weizixun.net.ApiService;
import com.anfly.weizixun.net.BaseObserver;
import com.anfly.weizixun.net.HttpManager;
import com.anfly.weizixun.net.RxUtil;

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
