package com.zhenmei.testsplashscreen.activity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.zhenmei.testsplashscreen.R;
import com.zhenmei.testsplashscreen.utils.SPUtils;

/**
 * 启动页面
 */
public class StartActivity extends BaseActivity implements Animation.AnimationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
    }

    private ImageView ivStart;

    /**
     * 初始化空间
     */
    private void initView() {
        ivStart = (ImageView) findViewById(R.id.iv_start);
        /**
         * 设置动画
         */
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.start_alpha);
        ivStart.startAnimation(animation);
        animation.setAnimationListener(this);
    }

    /**
     * 动画开始时
     *
     * @param animation
     */
    @Override
    public void onAnimationStart(Animation animation) {

    }

    /**
     * 动画结束时
     *
     * @param animation
     */
    @Override
    public void onAnimationEnd(Animation animation) {
        /**
         * 根据判断是否是第一次开启跳转到不同页面
         */
        boolean isFirst = SPUtils.getBoolean(this, "isFirstStart", true);
        if (isFirst) {
            goToNextActivity(GuideActivity.class);
        } else {
            goToNextActivity(MainActivity.class);
        }
    }

    /**
     * 动画重复时
     *
     * @param animation
     */
    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
