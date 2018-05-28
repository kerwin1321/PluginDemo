package com.example.skinlibrary.skinattr;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要换肤的属性
 * <p>
 * 封装类
 */
public abstract class SkinAttr {

    public static List<String> SKIN_ATTR_LIST = new ArrayList<>();

    static {
        SKIN_ATTR_LIST.add("textColor");
        SKIN_ATTR_LIST.add("background");
    }


    /**
     * 属性名称（textColor、background等）
     */
    public String attributeName;
    /**
     * 属性值的类型（@color、@drawable等）
     */
    public String resourceTypeName;
    /**
     * 属性值的资源名称（@drawable/bg中的bg）
     */
    public String resourceEntryName;
    /**
     * 属性值的资源id(R.id.bg)
     */
    public int resId;


    public SkinAttr(String attributeName, String resourceTypeName, String resourceEntryName, int resId) {
        this.attributeName = attributeName;
        this.resourceTypeName = resourceTypeName;
        this.resourceEntryName = resourceEntryName;
        this.resId = resId;
    }

    /**
     * 设置属性
     *
     * @param view
     */
    public abstract void apply(View view);

    /**
     * 创建SkinAttr对象
     *
     * @param attributeName
     * @param resourceTypeName
     * @param resourceEntryName
     * @param resId
     */
    public static SkinAttr create(String attributeName, String resourceTypeName, String resourceEntryName, int resId) {
        SkinAttr skinAttr = null;
        if ("textColor".equals(attributeName)) {
            skinAttr = new TextColorSkinAttr(attributeName, resourceTypeName, resourceEntryName, resId);
        } else if ("background".equals(attributeName)) {
            skinAttr = new BackgroundSkinAttr(attributeName, resourceTypeName, resourceEntryName, resId);
        }
        return skinAttr;
    }


}