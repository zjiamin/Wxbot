package com.tz.newlife.module.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.newlife.R;
import com.tz.newlife.module.base.BaseActivity;
import com.tz.newlife.module.been.News;
import com.tz.newlife.utils.GlideImageLoader;

public class NewsDetailsActivity extends BaseActivity {
    private Toolbar toolbar;

    private ImageView news_imgv;

    private TextView news_title;

    private TextView news_content;

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        intent = getIntent();
        initView();
        initDatas();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        //虽然这个ActionBar是由ToolBar来具体实现的，但是还是要用下面的方法来找到ActionBar的实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setDisplayShowTitleEnabled(false);//去掉toolbar左侧Title
        }

        news_title = (TextView) findViewById(R.id.tv_title);

        news_imgv = (ImageView) findViewById(R.id.imgv_pic);

        news_content = (TextView) findViewById(R.id.tv_content);

    }

    private void initDatas() {

        News news = (News) intent.getSerializableExtra("news_data");
        Log.e("initData_OUT", "onClick: " + news.getTitle() + "");
        if (intent != null) {
            Log.e("initData_IN", "onClick: " + news.getTitle() + "");
            news_title.setText(news.getTitle());
            new GlideImageLoader().displayImage(this, news.getPic(), news_imgv);
            news_content.setText(news.getContent());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
