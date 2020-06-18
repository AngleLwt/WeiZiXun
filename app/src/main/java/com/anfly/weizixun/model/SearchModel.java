package com.anfly.weizixun.model;

import com.anfly.weizixun.base.BaseModel;
import com.anfly.weizixun.bean.SearchBean;
import com.anfly.weizixun.callback.SearchCallback;
import com.anfly.weizixun.net.ApiService;
import com.anfly.weizixun.net.BaseObserver;
import com.anfly.weizixun.net.HttpManager;
import com.anfly.weizixun.net.RxUtil;

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
