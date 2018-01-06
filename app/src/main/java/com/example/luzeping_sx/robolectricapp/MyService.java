package com.example.luzeping_sx.robolectricapp;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by aria on 2018/1/4.
 */

public class MyService extends IntentService{

    public MyService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("SERVICE",MODE_PRIVATE).edit();
        editor.putString("data","serviceData");
        editor.apply();
    }
}
