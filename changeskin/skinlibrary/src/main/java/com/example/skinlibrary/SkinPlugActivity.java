package com.example.skinlibrary;

import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

public class SkinPlugActivity extends AppCompatActivity {

    protected SkinInflateFactory skinInfalteFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置解析xml监听器
        skinInfalteFactory = new SkinInflateFactory();
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), skinInfalteFactory);
        SkinPlugManager.getInstance().init(getApplicationContext());

        super.onCreate(savedInstanceState);
    }


    protected void updateSkin() {
        skinInfalteFactory.update();
    }


}
