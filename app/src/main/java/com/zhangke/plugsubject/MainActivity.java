package com.zhangke.plugsubject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }




    public void storage() {
        //获得sd卡挂载状态
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {//已挂载

            //   /storage/emulated/0
            System.out.println(Environment.getExternalStorageDirectory().getAbsolutePath());

            //   /storage/emulated/0/Android/data/com.zhangke.plugsubject/cache
            System.out.println(getExternalCacheDir().getAbsolutePath());

            //   /storage/emulated/0/Android/data/com.zhangke.plugsubject/files/image
            System.out.println(getExternalFilesDir("image").getAbsolutePath());

        }
    }

    public void data() {
        // 内部存储

        //   /data/data/com.zhangke.plugsubject/app_text
        System.out.println(getDir("text", Context.MODE_PRIVATE).getAbsolutePath());
        //   /data/data/com.zhangke.plugsubject/files
        System.out.println(getFilesDir().getAbsolutePath());
        //  /data/data/com.zhangke.plugsubject/cache
        System.out.println(getCacheDir().getAbsolutePath());

    }

    public void test(View view) {
        startActivity(new Intent(this, TestActivity.class));
    }
}
