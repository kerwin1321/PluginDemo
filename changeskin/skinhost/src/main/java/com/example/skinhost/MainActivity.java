package com.example.skinhost;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.example.skinlibrary.SkinPlugActivity;
import com.example.skinlibrary.SkinPlugManager;

import java.io.File;


public class MainActivity extends SkinPlugActivity implements View.OnClickListener {

    private Button btnDay;
    private Button btnNight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDay = findViewById(R.id.btn_day);
        btnNight = findViewById(R.id.btn_night);

        btnDay.setOnClickListener(this);
        btnNight.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_day:
                day();
                break;
            case R.id.btn_night:
                night();
                break;
        }

    }

    private void day() {
        SkinPlugManager.getInstance().changeSkin(null);
        updateSkin();
    }

    private void night() {
        requestPermission();
    }


    /**
     * 加载皮肤插件
     */
    private void loadSkinPlug() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File file = new File(externalStorageDirectory, "skinplug.apk");
        SkinPlugManager.getInstance().changeSkin(file.getAbsolutePath());

        updateSkin();
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                loadSkinPlug();

                return;
            }
        }
    }
}
