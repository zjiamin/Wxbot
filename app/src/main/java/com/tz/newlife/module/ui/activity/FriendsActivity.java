package com.tz.newlife.module.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tz.newlife.R;
import com.tz.newlife.adapters.Friends_Recycler_Adapter;
import com.tz.newlife.module.base.BaseActivity;
import com.tz.newlife.module.been.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends BaseActivity {

    public static final String FRIEND_DATA = "friend_data";

    private Toolbar toolbar;

    private RecyclerView recyclerView;

    private Friends_Recycler_Adapter adapter;

    private List<Fruit> friends_list = new ArrayList<>();

    private int[] photos = { R.mipmap.kaka, R.mipmap.girl, R.mipmap.momknows, R.mipmap.timg };

    private String[] names = { "iimouttatime", "BigHead", "给西贝雷斯披上围巾", "小草莓" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        initFriends();
        initView();
    }

    private void initFriends() {
        for (int i = 0; i < photos.length; i++) {
            friends_list.add(new Fruit(names[i], photos[i]));
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_layout);//直接找toolbar_layout里的id
        setSupportActionBar(toolbar);
        //虽然这个ActionBar是由ToolBar来具体实现的，但是还是要用下面的方法来找到ActionBar的实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setDisplayShowTitleEnabled(false);//去掉toolbar左侧Title
        }

        recyclerView = (RecyclerView) findViewById(R.id.friends_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new Friends_Recycler_Adapter(friends_list);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
