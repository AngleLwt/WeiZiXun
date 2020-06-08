package com.anfly.weizixun.common;

import android.app.Application;

public class WzxApplication extends Application {
    private static WzxApplication application;

    public static WzxApplication getApplication() {
        return application;
        //appkey:5edd92e3978eea085d11d57f
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
