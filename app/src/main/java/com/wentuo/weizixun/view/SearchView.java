package com.wentuo.weizixun.view;

import com.wentuo.weizixun.base.BaseView;
import com.wentuo.weizixun.bean.SearchBean;

public interface SearchView extends BaseView<SearchBean> {
    @Override
    void onSuccess(SearchBean searchBean);

    @Override
    void onFail(String error);
}
