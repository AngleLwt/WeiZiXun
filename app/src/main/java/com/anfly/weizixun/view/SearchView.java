package com.anfly.weizixun.view;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.base.BaseView;
import com.anfly.weizixun.bean.SearchBean;

public interface SearchView extends BaseView<SearchBean> {
    @Override
    void onSuccess(SearchBean searchBean);

    @Override
    void onFail(String error);
}
