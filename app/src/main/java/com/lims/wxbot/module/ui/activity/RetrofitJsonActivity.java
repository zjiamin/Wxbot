package com.lims.wxbot.module.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.lims.wxbot.R;
import com.lims.wxbot.adapters.MyAdapter;
import com.lims.wxbot.api.Service;
import com.lims.wxbot.module.been.Cook;
import com.lims.wxbot.module.been.Tngou;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iimouttatime on 2018/6/1.
 */

public class RetrofitJsonActivity extends AppCompatActivity implements Callback<Tngou> {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_json);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.tngou.net")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Service service = retrofit.create(Service.class);
        Call<Tngou> call = service.getList("cook",0, 1, 100);
        call.enqueue(this);
        lv = (ListView) findViewById(R.id.json_lv);

    }

    @Override
    public void onResponse(Call<Tngou> call, Response<Tngou> response) {
        Log.d("=======MESSAGE+++====",response.errorBody().toString());
//        List<Cook> list = response.body().getList();
//        lv.setAdapter(new MyAdapter(this,list));
        Toast.makeText(this, "请求成功:" + call.request().url(), Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onFailure(Call<Tngou> call, Throwable t) {
        Toast.makeText(this, "请求失败:" + call.request().url(), Toast.LENGTH_SHORT).show();
        t.printStackTrace();

    }
}
