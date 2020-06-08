package com.anfly.weizixun.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.anfly.weizixun.R;
import com.anfly.weizixun.base.BaseActivity;
import com.anfly.weizixun.base.BaseMvpActivity;
import com.anfly.weizixun.bean.LoginBean;
import com.anfly.weizixun.model.LoginMdel;
import com.anfly.weizixun.presenter.LoginPresenter;
import com.anfly.weizixun.view.LoginView;

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

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String name = etName.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        mPresenter.login(name, pwd);
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        toast("登录成功");
    }

    @Override
    public void onFail(String error) {
        toast(error);
    }
}
