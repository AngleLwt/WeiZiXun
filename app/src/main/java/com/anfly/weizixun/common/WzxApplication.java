package com.anfly.weizixun.common;

import android.app.Application;

public class WzxApplication extends Application {
    private static WzxApplication application;

    public static WzxApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
