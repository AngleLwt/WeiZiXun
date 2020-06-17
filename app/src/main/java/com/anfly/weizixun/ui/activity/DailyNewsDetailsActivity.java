package com.anfly.weizixun.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.anfly.weizixun.R;
import com.anfly.weizixun.base.BaseActivity;
import com.anfly.weizixun.base.BaseMvpActivity;
import com.anfly.weizixun.bean.DailyNewsDetailsBean;
import com.anfly.weizixun.common.Constants;
import com.anfly.weizixun.presenter.DailyNewsDetailsPresenter;
import com.anfly.weizixun.view.DailyNewsDetailsView;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyNewsDetailsActivity extends BaseMvpActivity<DailyNewsDetailsPresenter, DailyNewsDetailsView> implements DailyNewsDetailsView {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout ctl;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.html_text)
    HtmlTextView htmlText;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.cdl)
    CoordinatorLayout cdl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_daily_news_details;
    }

    @Override
    protected void initListener() {
        super.initListener();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(fab, "我是snackbar", Snackbar.LENGTH_INDEFINITE)
                        .setAction("动作", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(DailyNewsDetailsActivity.this, "点击了snackbar", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(Constants.IMAGE);
        String title = intent.getStringExtra(Constants.TITLE);
        int id = intent.getIntExtra(Constants.ID, 0);

        ctl.setTitle(title);
        Glide.with(this).load(imageUrl).into(iv);
        //张开时候标题颜色
        ctl.setExpandedTitleColor(Color.RED);
        //折叠时候标题颜色
        ctl.setCollapsedTitleTextColor(Color.WHITE);

        mPresenter.getData(id);
    }

    @Override
    protected DailyNewsDetailsView initMvpView() {
        return this;
    }

    @Override
    protected DailyNewsDetailsPresenter initMvpPresenter() {
        return new DailyNewsDetailsPresenter();
    }

    @Override
    public void onSuccess(DailyNewsDetailsBean dailyNewsDetailsBean) {
        String body = dailyNewsDetailsBean.getBody();
        htmlText.setHtml(body, new HtmlHttpImageGetter(htmlText));
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(DailyNewsDetailsActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}
