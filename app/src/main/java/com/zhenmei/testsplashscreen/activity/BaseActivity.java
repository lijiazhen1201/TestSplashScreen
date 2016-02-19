package com.zhenmei.testsplashscreen.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

/**
 * 基础Activity
 */
public class BaseActivity extends Activity {

    /**
     * 跳转到下一个Activity，结束当前Activity
     *
     * @param cls 要去的Activity
     */
    public void goToNextActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }

    /**
     * 跳转到下一个Activity
     *
     * @param cls 要去的Activity
     */
    public void goToActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * 弹出提示信息
     *
     * @param msg 信息
     */
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
