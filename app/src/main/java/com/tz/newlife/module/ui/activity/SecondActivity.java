package com.tz.newlife.module.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tz.newlife.R;
import com.tz.newlife.module.base.BaseActivity;

public class SecondActivity extends BaseActivity implements View.OnClickListener {
    private TextView et_sec;
    private Button btn_sec_result_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initEvent();
        passIntent();
    }

    private void passIntent() {
        Intent intent = getIntent();
        String intent_str = intent.getStringExtra("flag_intent");
        if (intent_str != "") {
            et_sec.setText(intent_str);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //用来存储跳转后本页面的临时数据
        String tempData = "Something u just tyed";
        outState.putString("data_key", tempData);
    }

    private void initView() {
        et_sec = (TextView) findViewById(R.id.et_second);
        btn_sec_result_return = (Button) findViewById(R.id.btn_sec_result_return);
    }

    private void initEvent() {
        btn_sec_result_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_sec_result_return:
                say_hi_to_main();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        监听用户按下back按钮时也可以执行回传say_hi_to_main
        say_hi_to_main();
    }

    public void say_hi_to_main () {
        Toast.makeText(SecondActivity.this, "执行say_hi_to_main",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("datareturn","Hello, Main, this is Sec.");
        setResult(RESULT_OK, intent);
        finish();
        //2018/6/24
        //Android第一行代码72页
    }
}
