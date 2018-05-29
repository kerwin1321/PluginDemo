package com.zhangke.pluginlibray;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ProxyPluginActivity extends Activity {

    private String className;
    private ActivityInterface activityInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activity类名
        className = getIntent().getStringExtra("className");
        try {
            Class<?> activityClass = getClassLoader().loadClass(className);
            Constructor<?> constructor = activityClass.getConstructor(new Class[]{});
            Object activityObject = constructor.newInstance(new Object[]{});

            // 获取插件实例
            activityInterface = (ActivityInterface) activityObject;
            activityInterface.attach(this);

            Bundle bundle = new Bundle();
            activityInterface.onCreate(bundle);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        activityInterface.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityInterface.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityInterface.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInterface.onDestroy();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }


    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent intent1 = new Intent(this, ProxyPluginActivity.class);
        intent1.putExtra("className", className);
        super.startActivity(intent1);
    }

}
