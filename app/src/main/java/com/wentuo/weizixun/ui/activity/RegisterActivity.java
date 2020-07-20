package com.wentuo.weizixun.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wentuo.weizixun.R;
import com.wentuo.weizixun.base.BaseMvpActivity;
import com.wentuo.weizixun.bean.RegisterBean;
import com.wentuo.weizixun.presenter.RegisterPresenter;
import com.wentuo.weizixun.view.RegisterView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseMvpActivity<RegisterPresenter, RegisterView> implements RegisterView {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_register_im)
    Button btnRegisterIm;

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_register);
    }

    @Override
    protected RegisterView initMvpView() {
        return this;
    }

    @Override
    protected RegisterPresenter initMvpPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public void onSuccess(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")) {
            toast("注册成功");
        }
    }

    @Override
    public void onFail(String error) {
        toast(error);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_register, R.id.btn_register_im})
    public void onViewClicked(View view) {
        String name = etName.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btn_register:
                mPresenter.register(name, pwd, "", "");
                break;
            case R.id.btn_register_im:
                register(name, pwd);
                break;
        }
    }

    private void register(String name, String pwd) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(name, pwd);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    finish();
                } catch (HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "注册失败：" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
