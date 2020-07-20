package com.wentuo.weizixun.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.wentuo.weizixun.R;
import com.wentuo.weizixun.adapter.ITInfoVpAdapter;
import com.wentuo.weizixun.bean.ITTabBean;
import com.wentuo.weizixun.bean.ItInfoBean;
import com.wentuo.weizixun.net.ApiService;
import com.wentuo.weizixun.net.BaseObserver;
import com.wentuo.weizixun.net.HttpManager;
import com.wentuo.weizixun.net.RxUtil;
import com.wentuo.weizixun.ui.fragment.WxArticleListFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItActivity extends AppCompatActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.cl_top)
    ConstraintLayout clTop;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private ITInfoVpAdapter adapter;
    private ArrayList<ITTabBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        list = new ArrayList<>();
    }

    private void initData() {
        HttpManager.getHttpManager()
                .getApiService(ApiService.baseWanAndroidUrl, ApiService.class)
                .getItInfoData()
                .compose(RxUtil.rxFlowableTransformer())
                .subscribe(new BaseObserver<ItInfoBean>() {
                    @Override
                    public void onSuccess(ItInfoBean itInfoBean) {
                        List<ItInfoBean.DataBean> data = itInfoBean.getData();
                        for (int i = 0; i < data.size(); i++) {
                            ItInfoBean.DataBean dataBean = data.get(i);

                            ITTabBean itTabBean = new ITTabBean(dataBean.getName(), dataBean.getId(), true);
                            list.add(itTabBean);
                        }
                        refreshUi(list);
                    }
                });
    }

    private void refreshUi(ArrayList<ITTabBean> data) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isShow()) {
                titles.add(data.get(i).getName());
                fragments.add(new WxArticleListFragment(data.get(i).getId()));
            }
        }
        adapter = new ITInfoVpAdapter(getSupportFragmentManager(), fragments, titles);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }

    @OnClick(R.id.iv_menu)
    public void onViewClicked() {
        Intent intent = new Intent(this, ItTabChooseActivity.class);
        intent.putExtra("list", list);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            list = (ArrayList<ITTabBean>) data.getSerializableExtra("list");
            titles.clear();
            fragments.clear();
            refreshUi(list);
        }
    }
}
