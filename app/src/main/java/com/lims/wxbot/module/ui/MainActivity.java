package com.lims.wxbot.module.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lims.wxbot.R;
import com.lims.wxbot.api.Service;
import com.lims.wxbot.module.ui.activity.RetrofitJsonActivity;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by iimouttatime on 2018/6/1.
 */

public class MainActivity extends AppCompatActivity implements Callback<String> {
    private TextView tv_main;
    private Button btn_main;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.baidu.com")
                .addConverterFactory(
                        new Converter.Factory() {//Converter转换器
                            @Override
                            public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                                return new Converter<ResponseBody, String>() {

                                    @Override
                                    public String convert(ResponseBody value) throws IOException {
                                        return value.string();
                                    }
                                };
                            }
                        }
                ).build();
        Service service = retrofit.create(Service.class);
        Call<String> call = service.getBaidu();
        call.enqueue(this);//异步请求

        tv_main = (TextView) findViewById(R.id.text_main);
        btn_main = (Button) findViewById(R.id.btn_main);
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClick();
            }
        });
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        Toast.makeText(this, "请求成功:" + call.request().url(), Toast.LENGTH_SHORT).show();
//        tv_main.setText(response.body());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Toast.makeText(this, "请求失败:" + call.request().url(), Toast.LENGTH_SHORT).show();
        t.printStackTrace();
    }

    public void btnClick() {
        startActivity(new Intent(this, RetrofitJsonActivity.class));
    }
}
