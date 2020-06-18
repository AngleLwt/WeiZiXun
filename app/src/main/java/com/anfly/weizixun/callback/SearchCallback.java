package com.anfly.weizixun.callback;

import com.anfly.weizixun.base.BaseCallback;
import com.anfly.weizixun.bean.SearchBean;

public interface SearchCallback extends BaseCallback<SearchBean> {
    @Override
    void onSuccess(SearchBean searchBean);

    @Override
    void onFail(String error);
}
