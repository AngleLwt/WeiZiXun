package com.wentuo.weizixun.presenter;

import com.wentuo.weizixun.base.BasePresenter;
import com.wentuo.weizixun.bean.SearchBean;
import com.wentuo.weizixun.callback.SearchCallback;
import com.wentuo.weizixun.model.SearchModel;

public class SearchPresenter extends BasePresenter implements SearchCallback {

    private SearchModel model;

    @Override
    protected void initModel() {
        model = new SearchModel();
        addModel(model);
    }

    public void getSearchData(int page, String word) {
        model.getSearchData(page, word, this);
    }

    @Override
    public void onSuccess(SearchBean searchBean) {
        mView.onSuccess(searchBean);
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
}
