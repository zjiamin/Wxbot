package com.tz.newlife.module.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tz.newlife.R;
import com.tz.newlife.module.base.BaseActivity;
import com.tz.newlife.module.ui.fragment.AnotherRightFragment;
import com.tz.newlife.module.ui.fragment.RightFragment;

public class WideNewsActivity extends BaseActivity implements View.OnClickListener{
    private Button btn_1;
    private Button btn_2;

    private NewMainActivity nmActivity = new NewMainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wide_activity);
        initView();
    }

    private void initView() {
        btn_1 = (Button) findViewById(R.id.btn_left_fragment);
        btn_2 = (Button) findViewById(R.id.btn_send_message);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);

        replaceFragment(new RightFragment());

    }

    private void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.right_layout, fragment);
//        transaction.addToBackStack(null);//将right_fregment添加到返回栈中，使其不被销毁，
//                                         // 返回的时候还能显示出来
//        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left_fragment:
                replaceFragment(new AnotherRightFragment());
                break;
            case R.id.btn_send_message:
                //获取fragment的实例来调用其中的方法
//                LeftFragment leftFragment = (LeftFragment) getSupportFragmentManager().findFragmentById(R.id.left_fragment);
//
//                String message = leftFragment.sendMessage("Hello WideActivity, it's LeftFragment.");
//
//                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                //使用接口回调方法读取数据
                //使用方法跟实现点OnClickListener一样，可以在这个类实现接口也可以匿名内部类实现
                nmActivity.setCallBack(new NewMainActivity.CallBack() {
                    @Override
                    public void getResult(String result) {
                        Toast.makeText(WideNewsActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            default:
                break;
        }
    }

}
