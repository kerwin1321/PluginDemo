package com.zhangke.skindemo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    private String packageName;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageview);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadResources();
            }
        });
    }



    public void loadResources() {
        File file = new File(Environment.getExternalStorageDirectory(), "skinplugin.apk");
        Resources resources = loadSkinResources(file.getAbsolutePath());

        // 通过类加载器获取apk包的资源
        DexClassLoader dexClassLoader = new DexClassLoader(file.getAbsolutePath()
                , this.getDir("config", Context.MODE_PRIVATE).getAbsolutePath()
                , null, this.getClassLoader());
        try {
            Class<?> imageClass = dexClassLoader.loadClass(packageName + ".R$drawable");
            Field image = imageClass.getDeclaredField("image");
            int anInt = image.getInt(R.drawable.class);
            Drawable drawable = resources.getDrawable(anInt);
            imageView.setImageDrawable(drawable);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    private Resources loadSkinResources(String path) {
        // 获取皮肤包信息
        PackageManager packageManager = getPackageManager();
        PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        packageName = packageArchiveInfo.packageName;

        try {

            // 创建AssetManager，并为该AssetManager对象设置资源路径
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(assetManager, path);

            // 创建获取皮肤资源的Resources
            Resources resources = new Resources(assetManager
                    , getResources().getDisplayMetrics()
                    , getResources().getConfiguration());

            return resources;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
