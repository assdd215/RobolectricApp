package com.example.luzeping_sx.robolectricapp.http;

import com.example.luzeping_sx.robolectricapp.Book;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by aria on 2018/1/23.
 */

public interface BookService {

    @GET("book/search")
    Observable<Book> searchBook(@Query("q")String name,
                                @Query("tag")String tag,
                                @Query("start")int start,
                                @Query("count")int count);

}
