package com.zhangke.pluginlibray;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 插件管理器，用户获取插件App
 * <p>
 * Created by zhangke on 2018/5/28.
 */

public class PluginManager {
    private static final PluginManager ourInstance = new PluginManager();

    public static PluginManager getInstance() {
        return ourInstance;
    }

    private PluginManager() {
    }


    private DexClassLoader dexClassLoader;
    private Resources resources;
    private Context context;
    private String entryActivityName;


    /**
     * 加载PluginApp
     *
     * @param path
     */
    public void loadPlugin(String path) {
        // 通过插件apk的类加载器
        dexClassLoader = new DexClassLoader(path
                , context.getDir("plugin", Context.MODE_PRIVATE).getAbsolutePath()
                , null, context.getClassLoader());

        // 获取插件入口activity名称
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        entryActivityName = packageArchiveInfo.activities[0].name;


        // 创建AssetManager，并为该AssetManager对象设置资源路径
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(assetManager, path);

            // 创建获取皮肤资源的Resources
            resources = new Resources(assetManager
                    , context.getResources().getDisplayMetrics()
                    , context.getResources().getConfiguration());

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public String getEntryActivityName() {
        return entryActivityName;
    }


    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }

}
