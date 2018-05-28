package com.zhangke.pluginlibray;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * 插件APP Activity接口
 *
 * Created by zhangke on 2018/5/28.
 */
public interface ActivityInterface {

    public void onCreate(Bundle savedInstanceState);
    public void onStart();
    public void onResume();
    public void onPause();
    public void onStop();
    public void onDestroy();
    public void onSaveInstanceState(Bundle outState);
    public void onBackPressed();
    public boolean onTouchEvent(MotionEvent event);

    /**
     * 依赖宿主程序的上下文
     * @param activity
     */
    public void attach(Activity activity);
}
