package com.zhangke.mainapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.zhangke.pluginlibray.ActivityInterface;
import com.zhangke.pluginlibray.PluginManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PluginActivity extends Activity {

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




    /**
     * 开启插件页面
     *
     * @param context
     * @param className
     */
    public static void startActivity(Context context, String className) {
        Intent intent = new Intent();
        intent.setClass(context, PluginActivity.class);
        intent.putExtra("className", className);
        context.startActivity(intent);
    }
}
