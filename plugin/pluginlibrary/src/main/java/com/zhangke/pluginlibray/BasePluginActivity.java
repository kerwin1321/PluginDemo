package com.zhangke.pluginlibray;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class BasePluginActivity extends Activity implements ActivityInterface {

    /**
     * HOST 上下文
     */
    public Activity that;

    @Override
    public void attach(Activity activity) {
        this.that = activity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (that == null) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        if (that == null) {
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if (that == null) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (that == null) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (that == null) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (that == null) {
            super.onDestroy();
        }
    }


    @Override
    public void setContentView(int layoutResID) {
        if (that == null) {
            super.setContentView(layoutResID);
        } else {
            that.setContentView(layoutResID);
        }
    }


    @Override
    public void setContentView(View view) {
        if (that == null) {
            super.setContentView(view);
        } else {
            that.setContentView(view);
        }
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        if (that == null) {
            super.addContentView(view, params);
        } else {
            that.addContentView(view, params);
        }
    }


    @Override
    public <T extends View> T findViewById(int id) {
        if (that == null) {
            return super.findViewById(id);
        } else {
            return that.findViewById(id);
        }
    }

    @Override
    public Resources getResources() {
        if (that == null) {
            return super.getResources();
        } else {
            return that.getResources();
        }
    }


    @Override
    public ClassLoader getClassLoader() {
        if (that == null) {
            return super.getClassLoader();
        } else {
            return that.getClassLoader();
        }
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        if (that == null) {
            return super.getLayoutInflater();
        } else {
            return that.getLayoutInflater();
        }
    }


    @Override
    public WindowManager getWindowManager() {
        if (that == null) {
            return super.getWindowManager();
        } else {
            return that.getWindowManager();
        }
    }

    @Override
    public Window getWindow() {
        if (that == null) {
            return super.getWindow();
        } else {
            return that.getWindow();
        }
    }


    @Override
    public void finish() {
        if (that == null) {
            super.finish();
        } else {
            that.finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (that == null) {
            super.onBackPressed();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (that == null) {
            super.onSaveInstanceState(outState);
        }
    }


    public void startActivity(Class<? extends Activity> aClass) {
        Intent intent = new Intent();
        if (that == null) {
            intent.setClass(this, aClass);
            super.startActivity(intent);
        }else{

            intent.putExtra("className", aClass.getName());
            that.startActivity(intent);
        }
    }


}
