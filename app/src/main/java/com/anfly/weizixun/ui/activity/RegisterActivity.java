package com.anfly.weizixun.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.anfly.weizixun.R;
import com.anfly.weizixun.base.BaseMvpActivity;
import com.anfly.weizixun.bean.RegisterBean;
import com.anfly.weizixun.presenter.RegisterPresenter;
import com.anfly.weizixun.view.RegisterView;

import java.util.function.Predicate;

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

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        String name = etName.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        mPresenter.register(name, pwd, "", "");
    }
}
