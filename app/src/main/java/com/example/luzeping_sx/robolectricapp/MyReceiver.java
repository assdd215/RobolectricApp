package com.example.luzeping_sx.robolectricapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by aria on 2018/1/5.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences.Editor editor = context.getSharedPreferences("TEST", Context.MODE_PRIVATE).edit();
        String data = intent.getStringExtra("data");
        editor.putString("data", data);
        editor.apply();
    }
}
