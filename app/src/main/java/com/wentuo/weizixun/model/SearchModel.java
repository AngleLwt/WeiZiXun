package com.wentuo.weizixun.model;

import com.wentuo.weizixun.base.BaseModel;
import com.wentuo.weizixun.bean.SearchBean;
import com.wentuo.weizixun.callback.SearchCallback;
import com.wentuo.weizixun.net.ApiService;
import com.wentuo.weizixun.net.BaseObserver;
import com.wentuo.weizixun.net.HttpManager;
import com.wentuo.weizixun.net.RxUtil;

public class SearchModel extends BaseModel {
    public void getSearchData(int page, String word, SearchCallback callback) {
        HttpManager.getHttpManager()
                .getApiService(ApiService.baseWanAndroidUrl, ApiService.class)
                .getSearchData(page, word)
                .compose(RxUtil.rxFlowableTransformer())
                .subscribe(new BaseObserver<SearchBean>() {
                    @Override
                    public void onSuccess(SearchBean searchBean) {
                        callback.onSuccess(searchBean);
                    }
                });
    }
}
