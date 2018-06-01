package com.lims.wxbot.api;

import com.lims.wxbot.module.been.Tngou;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by iimouttatime on 2018/6/1.
 */

public interface Service {
    @GET("/")
    Call<String> getBaidu();

    @GET("api/{category}/list")
    Call<Tngou> getList(@Path("category") String path,
                        @Query("id") int id, @Query("page") int page, @Query("rows") int rows);
    @POST("api/{category}/list")
    @FormUrlEncoded //使用Field属性必须添加这个
    Call<Tngou> getListForPost(@Path("category") String path, @Field("id") int id, @Field("page") int page,
                               @Field("rows") int rows);

}
