package com.example.luzeping_sx.robolectricapp.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by aria on 2018/1/23.
 */

public class HttpManager {

    private Retrofit retrofit;
    private static HttpManager instance;

    private HttpManager(){
        init();
    }
    public void init(){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://api.douban.com/v2/")
                .build();
    }

    public BookService createBookService(){
        return retrofit.create(BookService.class);
    }

    public static <T>Subscription requestHttp(Observable<T> o,Subscriber<T> s){
        return o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    public static HttpManager getInstance(){
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }
}
