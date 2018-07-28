package com.tz.newlife.module.ui.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.tz.newlife.R;
import com.tz.newlife.module.base.BaseActivity;

public class SplashActivity extends BaseActivity {
    private boolean isSkip = false;
    //这样创建有内存泄漏
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
            @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            next();
            }
    };

    private void next() {
        if (!isSkip) {
            finish();
            startActivity(new Intent(this, NewMainActivity.class));
            isSkip = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initDatas();
    }

    protected void initDatas() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(-1);
            }
        }, 3000);
    }

}
