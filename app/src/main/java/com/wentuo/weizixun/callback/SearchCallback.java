package com.wentuo.weizixun.callback;

import com.wentuo.weizixun.base.BaseCallback;
import com.wentuo.weizixun.bean.SearchBean;

public interface SearchCallback extends BaseCallback<SearchBean> {
    @Override
    void onSuccess(SearchBean searchBean);

    @Override
    void onFail(String error);
}
