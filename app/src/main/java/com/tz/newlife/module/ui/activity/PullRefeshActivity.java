package com.tz.newlife.module.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tz.newlife.R;
import com.tz.newlife.adapters.Fruit_Recycler_Adapter;
import com.tz.newlife.module.base.BaseActivity;
import com.tz.newlife.module.been.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PullRefeshActivity extends BaseActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView nav_View;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;


    private Fruit[] fruits = {
            new Fruit("草莓", R.mipmap.caomei),//1
            new Fruit("好像是桃子", R.mipmap.youtao),//2
            new Fruit("橙子", R.mipmap.chengzi),//3
            new Fruit("西红柿", R.mipmap.tomato),//4
            new Fruit("青苹果", R.mipmap.qingapple),//5
            new Fruit("橘子", R.mipmap.juzi),//6
            new Fruit("石榴", R.mipmap.shiliu),//7
            new Fruit("蓝莓", R.mipmap.lanmei),//8
            new Fruit("西瓜", R.mipmap.melon),//9
            new Fruit("芒果", R.mipmap.mongo),//10
            new Fruit("猕猴桃", R.mipmap.miht),//11
            new Fruit("红苹果", R.mipmap.redapple),//12
            new Fruit("青果", R.mipmap.qingguo),//13
            new Fruit("香蕉", R.mipmap.banana),//14
            new Fruit("樱桃", R.mipmap.yingtao)};//15

    private List<Fruit> fruitList = new ArrayList<>();

    private Fruit_Recycler_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_refesh);
        initFruits();
        initView();
        initEvent();
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random  = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_View = (NavigationView) findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

        //虽然这个ActionBar是由ToolBar来具体实现的，但是还是要用下面的方法来找到ActionBar的实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(PullRefeshActivity.this, "FAB Clicked",
//                        Toast.LENGTH_SHORT).show();
                Snackbar.make(fab, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(PullRefeshActivity.this, "Data restored",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Fruit_Recycler_Adapter(fruitList);
        recyclerView.setAdapter(adapter);

        //下拉刷新
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);//下拉刷新进度条颜色
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {//通常情况下是请求网络数据
                refreshFruits();
            }
        });

    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();//通知数据发生变化
                        swipeRefresh.setRefreshing(false);//表示刷新事件结束，并隐藏进度条
                    }
                });
            }
        }).start();//注意别忘了start
    }

    private void initEvent() {
        nav_View.setCheckedItem(R.id.nav_call);
        nav_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
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
            case R.id.backup:
                Toast.makeText(PullRefeshActivity.this, "U click Backup",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(PullRefeshActivity.this, "U click Delete",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(PullRefeshActivity.this, "U click Settings",
                        Toast.LENGTH_SHORT).show();
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


}
