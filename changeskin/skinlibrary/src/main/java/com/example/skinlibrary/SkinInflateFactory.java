package com.example.skinlibrary;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.example.skinlibrary.skinattr.SkinAttr;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkinInflateFactory implements LayoutInflater.Factory2 {

    private static final String[] prefixList = {
            "android.widget.",
            "android.view.",
            "android.webkit."};


    /**
     * 存储每个View以及他们需要换肤的属性，更新的时候使用
     */
    public Map<View, SkinViewItem> attrViewMap = new HashMap<>();

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return this.onCreateView(null, name, context, attrs);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.indexOf(".") == -1) {
            for (String prefix : prefixList) {
                view = createView(context, attrs, prefix + name);
                if (view != null) {
                    break;
                }
            }
        } else {
            view = createView(context, attrs, name);
        }

        if (view != null) {
            parseViewAttrs(view, context, attrs);
        }
        return view;
    }


    /**
     * 通过反射创建view
     *
     * @param context
     * @param attrs
     * @param name
     * @return
     */
    private View createView(Context context, AttributeSet attrs, String name) {
        try {
            Class clazz = context.getClassLoader().loadClass(name);
            Constructor<? extends View> constructor = clazz.getConstructor(new Class[]{Context.class, AttributeSet.class});
            constructor.setAccessible(true);
            return constructor.newInstance(context, attrs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 解析需要换肤的属性
     *
     * @param view
     * @param context
     * @param attrs
     */
    private void parseViewAttrs(View view, Context context, AttributeSet attrs) {

        List<SkinAttr> attrList = new ArrayList<>();

        int attributeCount = attrs.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {

            SkinAttr skinAttr = null;

            String attributeName = attrs.getAttributeName(i);  // 属性名

            if (SkinAttr.SKIN_ATTR_LIST.contains(attributeName)) {

                String attributeValue = attrs.getAttributeValue(i);
                int resId = Integer.parseInt(attributeValue.substring(1));   // 资源id
                String resourceTypeName = context.getResources().getResourceTypeName(resId);   // 资源类型名称
                String resourceEntryName = context.getResources().getResourceEntryName(resId); // 资源名称

                // 根据属性名称创建一个属性
                skinAttr = SkinAttr.create(attributeName, resourceTypeName, resourceEntryName, resId);

            }

            // 存储所有需要换肤的属性
            if (skinAttr != null) {
                attrList.add(skinAttr);
            }
        }

        SkinViewItem skinViewItem = new SkinViewItem(view, attrList);
        skinViewItem.apply();

        // 缓存，当页面属性的使用使用
        attrViewMap.put(view, skinViewItem);
    }

    public void update() {
        for (View view : attrViewMap.keySet()) {
            if (view == null) {
                continue;
            }
            attrViewMap.get(view).apply();
        }
    }


    private class SkinViewItem {
        private View view;
        private List<SkinAttr> attrList;

        public SkinViewItem(View view, List<SkinAttr> attrList) {
            this.view = view;
            this.attrList = attrList;
        }

        public void apply() {
            for (SkinAttr skinAttr : attrList) {
                skinAttr.apply(view);
            }
        }
    }


}
