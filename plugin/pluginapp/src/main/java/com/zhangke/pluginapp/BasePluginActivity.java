package com.zhangke.pluginapp;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zhangke.pluginlibray.ActivityInterface;

public class BasePluginActivity extends Activity implements ActivityInterface {


    /**
     * HOST 上下文
     */
    public Activity hostActivity;

    @Override
    public <T extends View> T findViewById(int id) {
        if (hostActivity == null) {
            return super.findViewById(id);
        } else {
            return hostActivity.findViewById(id);
        }
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if (hostActivity == null) {
            return super.getLayoutInflater();
        } else {
            return hostActivity.getLayoutInflater();
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (hostActivity == null) {
            super.setContentView(layoutResID);
        } else {
            hostActivity.setContentView(layoutResID);
        }
    }

    @Override
    public Resources getResources() {
        if (hostActivity == null) {
            return super.getResources();
        } else {
            return hostActivity.getResources();
        }
    }


    @Override
    public ClassLoader getClassLoader() {
        if (hostActivity == null) {
            return super.getClassLoader();
        } else {
            return hostActivity.getClassLoader();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (hostActivity == null) {
            return super.getWindowManager();
        } else {
            return hostActivity.getWindowManager();
        }
    }

    @Override
    public Window getWindow() {
        if (hostActivity == null) {
            return super.getWindow();
        } else {
            return hostActivity.getWindow();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void attach(Activity activity) {
        this.hostActivity = activity;
    }
}
