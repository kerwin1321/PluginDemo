package com.example.skinlibrary.parseres;

import android.graphics.drawable.Drawable;

import com.example.skinlibrary.skinattr.SkinAttr;

public interface IResParse {

    /**
     * 获取颜色
     *
     * @param skinAttr
     * @return
     */
    int getColor(SkinAttr skinAttr);

    /**
     * 获取图片
     *
     * @param skinAttr
     * @return
     */
    Drawable getDrawable(SkinAttr skinAttr);


}
