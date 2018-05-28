package com.example.skinlibrary.skinattr;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.skinlibrary.SkinPlugManager;

class TextColorSkinAttr extends SkinAttr {


    public TextColorSkinAttr(String attrName, String attrValueResourceType, String attrValueResourceName, int resId) {
        super(attrName, attrValueResourceType, attrValueResourceName, resId);
    }

    @Override
    public void apply(View view) {

        if (view instanceof TextView) {

            TextView textView = (TextView) view;
            textView.setTextColor(SkinPlugManager.getInstance().getColor(this));
        }

    }
}
