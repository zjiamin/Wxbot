package com.tz.newlife.module.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tz.newlife.R;
import com.tz.newlife.adapters.HFruit_Recycler_Adapter;
import com.tz.newlife.module.base.BaseActivity;
import com.tz.newlife.module.been.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TasksActivity extends BaseActivity {
    private Toolbar toolbar;
    private SwipeRefreshLayout swipe_refresh;
    private RecyclerView recycler_view;
    private HFruit_Recycler_Adapter hFruit_recycler_adapter;

    private Fruit fruits[] = {
            new Fruit(getRandomLengthName("樱桃"), R.mipmap.yingtao),//1
            new Fruit(getRandomLengthName("草莓"), R.mipmap.caomei),//2
            new Fruit(getRandomLengthName("香蕉"), R.mipmap.banana),//3
            new Fruit(getRandomLengthName("红苹果"), R.mipmap.redapple),//4
            new Fruit(getRandomLengthName("青苹果"), R.mipmap.qingapple),//5
            new Fruit(getRandomLengthName("橘子"), R.mipmap.juzi),//6
            new Fruit(getRandomLengthName("青果"), R.mipmap.qingguo),//7
            new Fruit(getRandomLengthName("猕猴桃"), R.mipmap.miht),//8
            new Fruit(getRandomLengthName("油桃"), R.mipmap.youtao),//9
            new Fruit(getRandomLengthName("西红柿"), R.mipmap.tomato),//10
            new Fruit(getRandomLengthName("橙子"), R.mipmap.chengzi),//11
            new Fruit(getRandomLengthName("石榴"), R.mipmap.shiliu),//12
            new Fruit(getRandomLengthName("芒果"), R.mipmap.mongo),//13
            new Fruit(getRandomLengthName("西瓜"), R.mipmap.melon),//14
            new Fruit(getRandomLengthName("蓝莓"), R.mipmap.lanmei)};//15

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        initFruits();
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);

        //虽然这个ActionBar是由ToolBar来具体实现的，但是还是要用下面的方法来找到ActionBar的实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowTitleEnabled(false);//去掉toolbar左侧Title
        }
        //处理RecyclerView
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);//横向滑动的RecyClerView
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);//瀑布流布局
        recycler_view.setLayoutManager(layoutManager);
        hFruit_recycler_adapter = new HFruit_Recycler_Adapter(fruitList);
        recycler_view.setAdapter(hFruit_recycler_adapter);

        //处理下拉刷新
        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reFreshFruits();
            }
        });

    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random  = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    private void reFreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                runOnUiThread(new Runnable() {//刷新页面只能切换回主线程进行
                    @Override
                    public void run() {
                        initFruits();
                        //一定要notifyDataSetChanged通知数据发生变化，否则不刷新
                        hFruit_recycler_adapter.notifyDataSetChanged();
                        swipe_refresh.setRefreshing(false);//表示刷新事件结束，并隐藏进度条
                    }
                });
            }
        }).start();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            default:

        }
        return true;
    }


}
