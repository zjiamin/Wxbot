package com.tz.newlife.module.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tz.newlife.R;
import com.tz.newlife.adapters.ViewPagerAdapter;
import com.tz.newlife.module.base.BaseActivity;

public class NewMainActivity extends BaseActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView nav_View;
    private FloatingActionButton fab;

    private ViewPager view_pager;
    private TabLayout mTabLayout;
    private ViewPagerAdapter mAdpater;

    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;

    private String msg = "你好吗，王壮壮";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmain);

        initView();
        initEvent();
    }



    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setContentInsetsAbsolute(0, 0);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_View = (NavigationView) findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

        //虽然这个ActionBar是由ToolBar来具体实现的，但是还是要用下面的方法来找到ActionBar的实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.iconmonstr_menu_white);
            getSupportActionBar().setDisplayShowTitleEnabled(false);//去掉toolbar左侧Title
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(fab, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(NewMainActivity.this, "Data restored",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        view_pager = (ViewPager) findViewById(R.id.view_pager);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        mAdpater = new ViewPagerAdapter(getSupportFragmentManager());
        view_pager.setAdapter(mAdpater);
        mTabLayout.setupWithViewPager(view_pager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++){
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(mAdpater.getTabView(i, NewMainActivity.this));
        }



        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Log.d("TabLayout", "onTabSelected: " + 0);
                        break;
                    case 1:
                        Log.d("TabLayout", "onTabSelected: " + 0);
                        break;
                    case 2:
                        Log.d("TabLayout", "onTabSelected: " + 0);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }



    private void initEvent() {
        nav_View.setCheckedItem(R.id.nav_call);
        nav_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_call:
                        //调取联系人，动态申请权限
                        break;
                    case R.id.nav_friends:
                        startActivity(new Intent(NewMainActivity.this,
                                FriendsActivity.class));
                        break;
                    case R.id.nav_location:
                        //地图定位
                        startActivity(new Intent(NewMainActivity.this,
                                BMapActivity.class));
                        break;
                    case R.id.nav_mail:

                        break;
                    case R.id.nav_task:
                        startActivity(new Intent(NewMainActivity.this,
                                TasksActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    //加载toolbar.xml菜单文件
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    //处理toolbar中各个按钮的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                //通过接口回调传值
//                startActivity(new Intent(NewMainActivity.this,
//                        WideNewsActivity.class));

                startActivity(new Intent(NewMainActivity.this,
                        TabletNewsActivity.class));

                break;

            case android.R.id.home:
                //HomeAsUp的id永远都是android.R.id.home
                //DrawerLayout的openDrawer()方法是打开滑动菜单
                drawerLayout.openDrawer(GravityCompat.START);
                break;

                default:
        }

        return true;
    }

    //再次点击退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //定义回调接口
    public interface CallBack {
        public void getResult(String result);
    }
    //接口回调
    public void setCallBack(CallBack callBack) {
        callBack.getResult(msg);
    }

}
