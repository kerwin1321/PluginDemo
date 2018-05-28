package com.example.skinlibrary.skinattr;

import android.view.View;
import android.widget.ImageView;

import com.example.skinlibrary.SkinPlugManager;

/**
 * Created by zhangke on 2018/5/28.
 */

public class SrcSkinAttr extends SkinAttr {

    public SrcSkinAttr(String attributeName, String resourceTypeName, String resourceEntryName, int resId) {
        super(attributeName, resourceTypeName, resourceEntryName, resId);
    }

    @Override
    public void apply(View view) {

        if (view instanceof ImageView) {
            ((ImageView)view).setImageDrawable(SkinPlugManager.getInstance().getDrawable(this));
        }
    }
}
