package com.example.luzeping_sx.robolectricapp;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aria on 2018/1/5.
 */

public interface RetrofitService {

    @GET("book/search")
    Call<Book> getSearchBook(@Query("q")String name,
                             @Query("tag")String tag,
                             @Query("start")int start,
                             @Query("count")int count);
}