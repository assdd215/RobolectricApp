package com.example.luzeping_sx.robolectricapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.assertEquals;

/**
 * Created by aria on 2018/1/4.
 */

@RunWith(RobolectricTestRunner.class)
public class MyServiceTest {

    @Test
    public void TestService(){
        Application application = RuntimeEnvironment.application;
        MyService myService = Robolectric.setupIntentService(MyService.class);
        myService.onHandleIntent(new Intent());
        SharedPreferences preferences = application.getSharedPreferences("SERVICE", Context.MODE_PRIVATE);
        assertEquals(preferences.getString("data",""),"serviceData");
    }
}
