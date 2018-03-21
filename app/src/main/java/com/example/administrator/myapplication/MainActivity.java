package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import cn.iwgang.countdownview.CountdownView;
import cn.iwgang.countdownview.DynamicConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity  {

    private CountdownView mCvCountdownViewTest;
    private final long TIME = (long)8 * 24 * 60 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCvCountdownViewTest = findViewById(R.id.cv_countdownViewTest);
        DynamicConfig dynamicConfig1 = new DynamicConfig.Builder().setSuffixGravity(DynamicConfig.SuffixGravity.CENTER).build();
        DynamicConfig dynamicConfig = new DynamicConfig.Builder().setTimeTextColor(getResources().getColor(R.color.colorAccent)).build();
        mCvCountdownViewTest.dynamicShow(dynamicConfig1);
        mCvCountdownViewTest.dynamicShow(dynamicConfig);
        mCvCountdownViewTest.start(TIME);

    }


}
