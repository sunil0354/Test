package com.example.user.test;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
//    @GET("json")//your api link
//    Call<Object> login();

    @POST("login")
    Call<Object> login(@Body Map<String,String> body);

}
