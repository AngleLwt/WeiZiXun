package com.anfly.weizixun.common;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class WzxApplication extends Application {
    private static WzxApplication application;

    public static WzxApplication getApplication() {
        return application;
    }

    private void initUmeng() {
        //签名：aabdc775e668794f406883f14f208c5a
        //appkey:5edd92e3978eea085d11d57f

        //QQ
        //APP ID：1110507739
        //APP KEY：xl7pfkFZAGxAcq61

        //微博
        //App Key：2792486505
        //App Secret：ef26a058835189a009f92564a6572a50
        UMConfigure.init(this, "5edd92e3978eea085d11d57f", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");

        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setSinaWeibo("2792486505", "ef26a058835189a009f92564a6572a50","http://sns.whalecloud.com");
        PlatformConfig.setQQZone("1110507739", "xl7pfkFZAGxAcq61");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        initUmeng();
    }
}
