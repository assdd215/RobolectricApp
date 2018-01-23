package com.example.luzeping_sx.robolectricapp.shadow;

import com.example.luzeping_sx.robolectricapp.http.HttpManager;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by aria on 2018/1/23.
 */

@Implements(HttpManager.class)
public class ShadowHttpManager {

    @RealObject HttpManager httpManager; //获取对应的真实实例，虽然在这里没啥子用。


    public static final int STATE_NORMAL = 0;
    public static final int STATE_EMPTY = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_CUSTOMERIZE = 3;

    public static int mState = STATE_NORMAL;
    public static Throwable mThrowable = new Throwable("unknown exception");

    public static Object data;

    @Implementation
    public static <T> Subscription requestHttp(Observable<T> o, Subscriber<T> s){

        //添加了自己的测试逻辑
        switch (mState){
            case STATE_EMPTY:
            case STATE_CUSTOMERIZE:
                return Observable.create(new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        try {
                            T d = (T) data;
                            subscriber.onNext(d);
                        }catch (Exception e){
                            subscriber.onNext(null);
                        }

                    }
                }).subscribe(s);


            case STATE_ERROR:
                return Observable.create(new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        subscriber.onError(mThrowable != null ? mThrowable : new Throwable("unknown exception"));
                    }
                }).subscribe(s);

            case STATE_NORMAL:
            default:

        }
        return o.subscribe(s);  //将异步操作转为同步
    }
}
