package com.example.skinlibrary.skinattr;

import android.view.View;

import com.example.skinlibrary.SkinPlugManager;

class BackgroundSkinAttr extends SkinAttr {

    public BackgroundSkinAttr(String attributeName, String resourceTypeName, String resourceEntryName, int resId) {
        super(attributeName, resourceTypeName, resourceEntryName, resId);
    }

    @Override
    public void apply(View view) {

        if ("color".equals(resourceTypeName)) {

            view.setBackgroundColor(SkinPlugManager.getInstance().getColor(this));

        } else if ("drawable".equals(resourceTypeName)) {

            view.setBackgroundDrawable(SkinPlugManager.getInstance().getDrawable(this));
        }

    }
}
