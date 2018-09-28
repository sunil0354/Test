package com.example.user.test;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("json")//your api link
    Call<Object> getData();
}
