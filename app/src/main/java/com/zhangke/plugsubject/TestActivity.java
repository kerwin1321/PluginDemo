package com.zhangke.plugsubject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;

public class TestActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater from = LayoutInflater.from(this);
        System.out.println(from);
    }
}
