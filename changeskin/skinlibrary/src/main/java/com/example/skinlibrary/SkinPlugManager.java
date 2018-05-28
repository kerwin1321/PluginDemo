package com.example.skinlibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.example.skinlibrary.parseres.IResParse;
import com.example.skinlibrary.skinattr.SkinAttr;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 皮肤插件管理类，用于加载皮肤插件
 */
public class SkinPlugManager implements IResParse {

    private static final SkinPlugManager ourInstance = new SkinPlugManager();

    public static SkinPlugManager getInstance() {
        return ourInstance;
    }

    private SkinPlugManager() {

    }

    protected Context context;
    protected Resources skinResources;
    protected String skinPackageName;

    private Map<String, Resources> skinResMap = new HashMap<>();

    public void init(Context context) {
        this.context = context;
    }

    /**
     * 换肤
     *
     * @param path
     */
    public void changeSkin(String path) {
        // 加载原生资源
        if (path == null) {
            skinResources = null;
            return;
        }

        // 先从缓存中获取皮肤资源
        Resources resources = skinResMap.get(path);
        if (resources != null) {
            skinResources = resources;
            return;
        }

        // 从皮肤包中加载
        skinResources = loadSkinResources(path);
    }

    /**
     * 根据皮肤包获取该皮肤包的Resources对象
     *
     * @param path 皮肤包路径
     */
    private Resources loadSkinResources(String path) {
        // 获取皮肤包信息
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        skinPackageName = packageArchiveInfo.packageName;
        try {

            // 创建AssetManager，并为该AssetManager对象设置资源路径
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(assetManager, path);

            // 创建获取皮肤资源的Resources
            Resources resources = new Resources(assetManager
                    , context.getResources().getDisplayMetrics()
                    , context.getResources().getConfiguration());

            // 缓存，用于多个皮肤切换时使用
            skinResMap.put(path, resources);
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


    /**
     * 获取颜色值
     * <p>
     * 1、如果skinResources为空，那么表示皮肤包不存在，那么直接使用原来的res
     * 2、当skinResources不为空：
     * 1、根据
     *
     * @param skinAttr
     * @return
     */
    @Override
    public int getColor(SkinAttr skinAttr) {
        if (skinResources == null) {
            return ContextCompat.getColor(context, skinAttr.resId);
        }

        int skinResId = this.getSkinResId(skinAttr.resourceEntryName, "color");
        int color = skinResources.getColor(skinResId);
        return color;
    }

    @Override
    public Drawable getDrawable(SkinAttr skinAttr) {
        Drawable drawable = ContextCompat.getDrawable(context, skinAttr.resId);
        if (skinResources == null) {
            return drawable;
        }

        int skinResId = this.getSkinResId(skinAttr.resourceEntryName, "drawable");
        Drawable skinDrawable = skinResources.getDrawable(skinResId);
        return skinDrawable;
    }

    /**
     * 获取皮肤包中的属性id
     *
     * @param resourceEntryName
     * @param type
     * @return
     */
    private int getSkinResId(String resourceEntryName, String type) {
        return skinResources.getIdentifier(resourceEntryName, type, skinPackageName);
    }


}
