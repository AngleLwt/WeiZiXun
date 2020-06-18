package com.anfly.weizixun.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.anfly.weizixun.R;
import com.anfly.weizixun.base.BaseActivity;
import com.anfly.weizixun.common.Constants;
import com.anfly.weizixun.ui.fragment.ContactsFragment;
import com.anfly.weizixun.ui.fragment.CoversationFragment;
import com.anfly.weizixun.ui.fragment.DiscoveryFragment;
import com.anfly.weizixun.utils.SharedPreferencesUtils;
import com.anfly.weizixun.utils.UIModeUtil;
import com.baidu.mapapi.search.share.ShareUrlResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tab_main)
    TabLayout tabMain;
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.cl_main)
    ConstraintLayout clMain;
    @BindView(R.id.nv_main)
    NavigationView nvMain;
    @BindView(R.id.dl)
    DrawerLayout dl;
    private FragmentManager fragmentManager;
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    private int lastPositoin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        EventBus.getDefault().register(this);
        int mode = (int) SharedPreferencesUtils.getParam(this, Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
        UIModeUtil.setAppMode(mode);

        toolbarMain.setLogo(R.mipmap.ic_launcher);
        toolbarMain.setTitle(getResources().getString(R.string.conversation));
        toolbarMain.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbarMain);
        nvMain.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbarMain, R.string.app_name, R.string.app_name);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();
        initPermissions();
        initTabTitles();
        initTabs();
        initFragments();
        showDefaultFragment();
    }

    private void showDefaultFragment() {
        switchFragment(0);
    }

    private void switchFragment(int position) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment curFragment = fragments.get(position);
        Fragment lastFragment = fragments.get(lastPositoin);

        if (!curFragment.isAdded()) {
            transaction.add(R.id.fl_container, curFragment);
        }

        transaction.hide(lastFragment).show(curFragment);
        transaction.commit();
        lastPositoin = position;
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        CoversationFragment coversationFragment = new CoversationFragment();
        ContactsFragment contactsFragment = new ContactsFragment();
        DiscoveryFragment discoveryFragment = new DiscoveryFragment();

        if (!fragments.contains(coversationFragment)) {
            fragments.add(coversationFragment);
        }

        if (!fragments.contains(contactsFragment)) {
            fragments.add(contactsFragment);
        }
        if (!fragments.contains(discoveryFragment)) {
            fragments.add(discoveryFragment);
        }
    }

    private void initTabs() {
        for (int i = 0; i < titles.size(); i++) {
            tabMain.addTab(tabMain.newTab().setText(titles.get(i)));
        }
    }

    private void initTabTitles() {
        titles = new ArrayList<>();
        titles.add(getResources().getString(R.string.conversation));
        titles.add(getResources().getString(R.string.contacts));
        titles.add(getResources().getString(R.string.discovery));
    }

    private void initPermissions() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        //切换fragment
        tabMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switchFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //侧滑头部处理
        View headerView = nvMain.getHeaderView(0);
        ImageView iv_dl_header = headerView.findViewById(R.id.iv_dl_header);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.circleCrop();
        Glide.with(this).load(R.drawable.a).apply(requestOptions).into(iv_dl_header);
        iv_dl_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击头部", Toast.LENGTH_SHORT).show();
            }
        });

        //主界面跟随侧滑在x轴方向移动
        dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                int right = drawerView.getRight();
                clMain.setX(right);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        //侧滑
        nvMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_zhihu:

                        break;
                    case R.id.item_wechat:
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        break;
                    case R.id.item_map:

                        break;
                    case R.id.item_navigation:
                        startActivity(new Intent(MainActivity.this, NavigationActivity.class));
                        break;
                    case R.id.item_group:
                        Intent intent = new Intent(MainActivity.this, ChatGrouopActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(String msg) {
        finish();
    }
}
