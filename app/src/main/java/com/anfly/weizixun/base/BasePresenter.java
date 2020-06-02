package com.anfly.weizixun.base;

import java.util.ArrayList;

public abstract class BasePresenter {
    protected BaseView mView;
    ArrayList<BaseModel> models = new ArrayList<>();


    public void setmView(BaseView mView) {
        this.mView = mView;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    protected void onDestroy() {
        mView = null;
        if (models.size() > 0) {
            for (BaseModel model : models) {
                model.onDestroy();
            }
            models.clear();
        }
    }

    protected void addModel(BaseModel model) {
        models.add(model);
    }
}
