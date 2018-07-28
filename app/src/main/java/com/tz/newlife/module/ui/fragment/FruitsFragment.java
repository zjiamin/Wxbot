package com.tz.newlife.module.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tz.newlife.R;
import com.tz.newlife.adapters.Fruit_Recycler_Adapter;
import com.tz.newlife.module.been.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FruitsFragment extends Fragment {
    private Fruit[] fruits = {
            new Fruit("草莓", R.mipmap.caomei),//1
            new Fruit("油桃", R.mipmap.youtao),//2
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

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fruits, container, false);
        initFruits();
        initView(v);

        return v;
    }

    private void initView(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Fruit_Recycler_Adapter(fruitList);
        recyclerView.setAdapter(adapter);

        //下拉刷新
        swipeRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);//下拉刷新进度条颜色
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {//通常情况下是请求网络数据
                refreshFruits();
            }
        });
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random  = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
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
                getActivity().runOnUiThread(new Runnable() {
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
}
