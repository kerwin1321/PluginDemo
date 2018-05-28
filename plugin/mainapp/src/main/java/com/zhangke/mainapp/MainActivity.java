package com.zhangke.mainapp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhangke.pluginlibray.PluginManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPlugin();
            }
        });

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlugin();

            }
        });
    }


    private void loadPlugin() {
        PluginManager.getInstance().setContext(this);

        File pluginFile = new File(Environment.getExternalStorageDirectory(), "pluginapp.apk");
        PluginManager.getInstance().loadPlugin(pluginFile.getAbsolutePath());
    }

    private void showPlugin() {

        PluginActivity.startActivity(this, PluginManager.getInstance().getEntryActivityName());
    }

}
