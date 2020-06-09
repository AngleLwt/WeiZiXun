package com.anfly.weizixun.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.anfly.weizixun.R;
import com.anfly.weizixun.base.BaseMvpActivity;
import com.anfly.weizixun.bean.LoginBean;
import com.anfly.weizixun.common.Constants;
import com.anfly.weizixun.presenter.LoginPresenter;
import com.anfly.weizixun.utils.SharedPreferencesUtils;
import com.anfly.weizixun.view.LoginView;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<LoginPresenter, LoginView> implements LoginView {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_share)
    ImageView btnShare;
    @BindView(R.id.iv_qq)
    ImageView ivQq;
    @BindView(R.id.iv_wx)
    ImageView ivWx;
    @BindView(R.id.iv_sina)
    ImageView ivSina;
    private String TAG = "LoginActivity";

    @Override
    protected LoginView initMvpView() {
        return this;
    }

    @Override
    protected LoginPresenter initMvpPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        toast(loginBean.getMessage());
    }

    @Override
    public void onFail(String error) {
        toast(error);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void initPermission() {
        String[] pers = {
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        ActivityCompat.requestPermissions(this, pers, 100);
    }

    @Override
    protected void initView() {
        super.initView();
        initPermission();
        if (EMClient.getInstance().isLoggedInBefore()) {
            goToMainActivity();
        }
    }

    private void share() {
        //分享图片、分享文本（可以单独分享）
        UMImage image = new UMImage(this, "http://ww4.sinaimg.cn/large/7a8aed7bjw1exp4h479xfj20hs0qoq6t.jpg");
        UMImage thumb = new UMImage(this, R.drawable.umeng_socialize_copy);
        image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
//        new ShareAction(LoginActivity.this)
//                .withText("我是美女")//文本
//                .withMedias(image, image)//分享的图片
//                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE)//三方列表
//                .setCallback(umShareListener)//分享回调
//                .open();

        //网页分享
        UMWeb web = new UMWeb("www.baidu.com");
        web.setTitle("大飞官网");//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription("这是我们自己的网站");//描述
        new ShareAction(LoginActivity.this)
                .withMedia(web)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE)//三方列表
                .setCallback(umShareListener)//分享回调
                .open();

    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Log.d(TAG, "onStart: " + share_media);
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Log.d(TAG, "onResult: " + share_media);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Log.d(TAG, "onError: " + throwable.toString());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Log.d(TAG, "onCancel: " + share_media);
        }
    };


    @OnClick({R.id.btn_login, R.id.btn_share, R.id.iv_qq, R.id.iv_wx, R.id.iv_sina, R.id.btn_register
            , R.id.btn_login_im})
    public void onViewClicked(View view) {
        String name = etName.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btn_login:
                mPresenter.login(name, pwd);
                break;
            case R.id.btn_login_im:
                loginIM(name, pwd);
                break;
            case R.id.btn_share:
                share();
                break;
            case R.id.iv_qq:
                login(SHARE_MEDIA.QQ);
                break;
            case R.id.iv_wx:
                login(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.iv_sina:
                login(SHARE_MEDIA.SINA);
                break;
            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private void loginIM(String name, String pwd) {
        EMClient.getInstance().login(name, pwd, new EMCallBack() {
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.e("TAG", "登录成功！");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        SharedPreferencesUtils.setParam(LoginActivity.this, Constants.NAME, name);
                        goToMainActivity();
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                Log.e("TAG", "登录失败：" + s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void login(SHARE_MEDIA media) {
        UMShareAPI umShareAPI = UMShareAPI.get(this);
        //media,三方平台
        //authListener,登录回调
        umShareAPI.getPlatformInfo(this, media, authListener);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Log.e("TAG", platform.getName() + ":LoginActivity onStart()");
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            logMap(data);
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Log.e("TAG", platform.getName() + "LoginActivity onComplete()");
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Log.e("TAG", platform.getName() + "LoginActivity onError()");
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Log.e("TAG", platform.getName() + "LoginActivity onCancel()");
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    private void logMap(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            Log.e("TAG", "logMap: " + entry.getKey() + "," + entry.getValue());
        }
    }
}
