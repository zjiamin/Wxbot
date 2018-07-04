package com.tz.newlife.module.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tz.newlife.R;
import com.tz.newlife.module.ui.activity.Http_U_C_Activity;
import com.tz.newlife.module.ui.activity.PullRefeshActivity;
import com.tz.newlife.module.ui.activity.SecondActivity;
import com.tz.newlife.module.base.BaseActivity;
import com.tz.newlife.module.ui.activity.WebViewActivity;


/**
 * 第一行代码前两章完结，77页。 第三章UI先稍一稍，先看看网络请求和Handler
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_main;
    private Button btn_to_http;
    private Button in_btn_main;
    private Button btn_intent_result;
    private Button btn_to_webView;
    private Button btn_to_refresh;
    private Button btn_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            et_main.setText(savedInstanceState.getString("main_data_key"));
        }
        initView();
        initEvent();
        getContent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = et_main.getText().toString();
        outState.putString("main_data_key", tempData);
    }

    public void initView() {
        Log.d("200", "initView: start");
        et_main = (EditText) findViewById(R.id.et_main);
        btn_to_http  = (Button) findViewById(R.id.btn_to_http);
        in_btn_main  = (Button) findViewById(R.id.intent_btn_main);
        btn_intent_result  = (Button) findViewById(R.id.intent_result_btn_main);
        Log.d("200", "initView: end");
        btn_to_webView = (Button) findViewById(R.id.btn_to_webView);
        btn_to_refresh = (Button) findViewById(R.id.btn_to_refresh);
        btn_stop = (Button) findViewById(R.id.stop_btn_main);
    }

    public void initEvent() {
        btn_to_http.setOnClickListener(this);
        in_btn_main.setOnClickListener(this);
        btn_intent_result.setOnClickListener(this);
        btn_to_webView.setOnClickListener(this);
        btn_to_refresh.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_http:
                //网络请求
                startActivity(new Intent(MainActivity.this,
                        Http_U_C_Activity.class));
                break;
            case R.id.intent_btn_main:
                //跳转到Second
                Intent intent = new Intent("com.tz.newlife.module.ui.activity.ACTION_START");
                intent.putExtra("flag_intent", "hello sec, this is Main");
                startActivity(intent);
                break;
            case R.id.intent_result_btn_main:
                //for_result
                startActivityForResult(new Intent(MainActivity.this,
                        SecondActivity.class), 1);
                break;

            case R.id.btn_to_webView:
                //to webView
                startActivity(new Intent(MainActivity.this,
                        WebViewActivity.class));

            case R.id.btn_to_refresh:
                startActivity(new Intent(MainActivity.this,
                        PullRefeshActivity.class));

            case R.id.stop_btn_main:
                //销毁Activities,杀死进程
                ActivityController.finishAll();
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
        }
    }

    private void getContent() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("datareturn");
                    Log.d("MainActivity", returnedData);
                    et_main.setText(returnedData);
                }
                break;
        }
    }
}
