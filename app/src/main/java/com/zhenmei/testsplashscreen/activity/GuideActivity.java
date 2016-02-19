package com.zhenmei.testsplashscreen.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhenmei.testsplashscreen.R;
import com.zhenmei.testsplashscreen.adapter.GuideAdapter;
import com.zhenmei.testsplashscreen.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    /**
     * 引导点的布局
     */
    private LinearLayout llDots;

    /**
     * 滑动页
     */
    private ViewPager vpGuide;

    /**
     * 引导图片的资源id
     */
    private int[] resIds = {R.mipmap.zxx, R.mipmap.llq, R.mipmap.tsh, R.mipmap.bml};

    /**
     * 引导图片的集合
     */
    private List<ImageView> list;

    /**
     * 初始化控件
     */
    private void initView() {
        llDots = (LinearLayout) findViewById(R.id.ll_dots);
        vpGuide = (ViewPager) findViewById(R.id.vp_guide);
        list = new ArrayList<>();

        for (int i = 0; i < resIds.length; i++) {
            /**
             * 动态添加引导图片
             */
            ImageView img = new ImageView(this);
            img.setImageResource(resIds[i]);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            RelativeLayout.LayoutParams lpImg=new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            img.setLayoutParams(lpImg);
            list.add(img);
            /**
             * 动态添加引导点
             */
            ImageView dot = new ImageView(this);
            dot.setImageResource(R.mipmap.dot_unselect);
            dot.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams lpDot = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lpDot.setMargins(10, 0, 10, 0);
            dot.setLayoutParams(lpDot);
            llDots.addView(dot);
        }
        /**
         * ViewPager设置适配器
         */
        GuideAdapter adapter = new GuideAdapter(list);
        vpGuide.setAdapter(adapter);
        /**
         * 第一个点设置为选中状态
         */
        ImageView dot = (ImageView) llDots.getChildAt(0);
        dot.setImageResource(R.mipmap.dot_select);
        /**
         * 最后一个页面设置点击事件监听
         */
        list.get(resIds.length - 1).setOnClickListener(this);
        /**
         * ViewPager设置事件监听
         */
        vpGuide.setOnPageChangeListener(this);
    }

    /**
     * 清空点的状态
     */
    private void clearDots() {
        for (int i = 0; i < resIds.length; i++) {
            ImageView dot = (ImageView) llDots.getChildAt(i);
            dot.setImageResource(R.mipmap.dot_unselect);
        }
    }

    /**
     * ViewPager的事件监听
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        /**
         * 当滑动到最后一页时，隐藏所有的点
         * 其他情况下则显示出来
         */
        if (position == resIds.length - 1) {
            llDots.setVisibility(View.GONE);
        } else {
            llDots.setVisibility(View.VISIBLE);
        }
        /**
         * 清空点的状态
         */
        clearDots();
        /**
         * 设置选中页的点为选中状态
         */
        ImageView dot = (ImageView) llDots.getChildAt(position);
        dot.setImageResource(R.mipmap.dot_select);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 最后一个引导图片的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        /**
         * 重置是否是第一次开启
         */
        SPUtils.setBoolean(this, "isFirstStart", false);
        /**
         * 跳转到主页面
         */
        goToNextActivity(MainActivity.class);
    }
}
