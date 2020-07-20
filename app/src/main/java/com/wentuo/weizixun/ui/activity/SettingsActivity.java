package com.wentuo.weizixun.ui.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.wentuo.weizixun.R;
import com.wentuo.weizixun.base.BaseActivity;
import com.wentuo.weizixun.common.WzxApplication;
import com.wentuo.weizixun.utils.UIModeUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.tv_mode)
    TextView tvMode;
    @BindView(R.id.sc)
    SwitchCompat sc;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setttings;
    }

    @Override
    protected void initView() {
        super.initView();
        if (WzxApplication.mMode == AppCompatDelegate.MODE_NIGHT_NO) {
            sc.setChecked(false);
        } else {
            sc.setChecked(true);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判断是否是用户点击
                if (buttonView.isPressed()) {
                    UIModeUtil.changeModeUI(SettingsActivity.this);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 是否触发按键为back键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //发消息关闭activity
            EventBus.getDefault().post("关闭Main");

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        // 如果不是back键正常响应
        return super.onKeyDown(keyCode, event);
    }
}
