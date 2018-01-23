package com.example.luzeping_sx.robolectricapp.http;

import android.os.Build;
import android.util.Log;

import com.example.luzeping_sx.robolectricapp.Book;
import com.example.luzeping_sx.robolectricapp.RetrofitService;
import com.example.luzeping_sx.robolectricapp.http.HttpManager;
import com.example.luzeping_sx.robolectricapp.shadow.ShadowHttpManager;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by aria on 2018/1/5.
 */


@RunWith(RobolectricTestRunner.class)
public class ApiTest {

    private final String TAG = "ApiTest";
    private Retrofit retrofit;
    private RetrofitService retrofitService;

    @Before
    public void setUp(){
        ShadowLog.stream = System.out;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        assertEquals(23, Build.VERSION.SDK_INT);
        retrofitService = retrofit.create(RetrofitService.class);
    }

    @Test
    public void TestApi(){
        try {
            Call<Book> call = retrofitService.getSearchBook("边城",null,0,1);
            Response<Book> response = call.execute();
            Gson gson = new Gson();
            Log.d(TAG,gson.toJson(response));
            assertNotNull(response);
            assertNotNull(response.body());

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void TestSyncRequest(){
        HttpManager.requestHttp(HttpManager.getInstance().createBookService().searchBook("边城", null, 0, 1), new Subscriber<Book>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Book book) {
                System.out.println("onNext:" + book.getCount());
            }
        });

        ShadowHttpManager.mState = ShadowHttpManager.STATE_ERROR; //测试错误返回后的逻辑
        HttpManager.requestHttp(HttpManager.getInstance().createBookService().searchBook("边城", null, 0, 1), new Subscriber<Book>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.getMessage());
            }

            @Override
            public void onNext(Book book) {
                System.out.println("onNext:" + book.getCount());
            }
        });
    }
}
