package com.wentuo.weizixun.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {
    private CompositeDisposable compositeDisposable;

    protected void onDestroy() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    protected void addModel(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
}
