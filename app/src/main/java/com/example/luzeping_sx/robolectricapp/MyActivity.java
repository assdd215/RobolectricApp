package com.example.luzeping_sx.robolectricapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by aria on 2018/1/4
 */

public class MyActivity extends AppCompatActivity{

    TextView mTextView;
    CheckBox mCheckBox;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        init();
    }

    private void init(){
        final View button = findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyActivity.this,MainActivity.class));
            }
        });
        mTextView = findViewById(R.id.textView);
        mTextView.setText("onCreate");



        mCheckBox = findViewById(R.id.checkbox);

        findViewById(R.id.inverseBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCheckBox.setChecked(!mCheckBox.isChecked());
            }
        });


        findViewById(R.id.dialogBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(MyActivity.this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("test dialog")
                        .setMessage("test message")
                        .create();
                dialog.show();
            }
        });

        findViewById(R.id.toastBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyActivity.this,"test toast",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTextView.setText("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTextView.setText("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTextView.setText("onPause");
    }
}
