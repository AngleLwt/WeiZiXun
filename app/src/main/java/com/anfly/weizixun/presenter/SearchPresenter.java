package com.anfly.weizixun.presenter;

import com.anfly.weizixun.base.BasePresenter;
import com.anfly.weizixun.bean.SearchBean;
import com.anfly.weizixun.callback.SearchCallback;
import com.anfly.weizixun.model.SearchModel;

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
