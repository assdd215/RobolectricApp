package com.example.luzeping_sx.robolectricapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by aria on 2018/1/5.
 */


@RunWith(RobolectricTestRunner.class)
public class MyReceiverTest {

    @Test
    public void TestBroadcast(){
        ShadowApplication shadowApplication = ShadowApplication.getInstance();

        String action = "com.example.luzeping_sx.BRADCAST";
        Intent intent = new Intent(action);
        intent.putExtra("data","myData");
        assertTrue(shadowApplication.hasReceiverForIntent(intent));

        MyReceiver myReceiver = new MyReceiver();
        myReceiver.onReceive(RuntimeEnvironment.application,intent);
        SharedPreferences preferences = shadowApplication.getApplicationContext()
                .getSharedPreferences("TEST", Context.MODE_PRIVATE);
        assertEquals("myData",preferences.getString("data",""));
    }
}
