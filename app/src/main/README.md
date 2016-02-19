# TestSplashScreen

## 欢迎界面＋引导界面

利用SharedPreferences存储布尔值实现引导页面只展示一次

	public static boolean getBoolean(Context mContext, String key, boolean defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }
    
    public static void setBoolean(Context mContext, String key, boolean value) {
    	SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    	sp.edit().putBoolean(key, value).commit();
    }
    
开始动画start_alpha.xml

	<?xml version="1.0" encoding="utf-8"?>
	<set xmlns:android="http://schemas.android.com/apk/res/android">
	    <alpha
	        android:duration="1000"
	        android:fromAlpha="1.0"
	        android:toAlpha="1.0" />
	</set>
	
开启动画，并设置监听

	ivStart = (ImageView) findViewById(R.id.iv_start);
	Animation animation = AnimationUtils.loadAnimation(this, R.anim.start_alpha);
	ivStart.startAnimation(animation);
	animation.setAnimationListener(this);
	
动画结束时判断是否时第一次开启app

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
    
引导页面，引导图片＋引导点

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
    
选中页面时

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
    
最后一页

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