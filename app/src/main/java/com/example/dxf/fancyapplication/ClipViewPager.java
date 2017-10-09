package com.example.dxf.fancyapplication;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashMap;

public class ClipViewPager extends ViewPager {
    private static final String TAG = "ClipViewPager";
    private HashMap<Integer, View> viewMap = new HashMap<>();
    private float downX;
    private int currentItem;

    public ClipViewPager(Context context) {
        super(context);
    }

    public ClipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        HomeShufAdapter mShufAdapter = (HomeShufAdapter) getAdapter();
        viewMap = mShufAdapter.getViewMap();
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downX = ev.getX();
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (ev.getX() == ev.getRawX()) {
                // 点击的是当前ViewPager的父控件
                View view = viewOfClickOnScreen(ev);
                if (view != null) {
                    setCurrentItem(currentItem);
                }
            } else {
                if (Math.abs(downX - ev.getX()) < 10) {
                    View view = viewOfClickOnScreen(ev);
                    if (view != null) {
                        RelativeLayout fancyRl = (RelativeLayout) view.findViewById(R.id.item_fancy_rl);
                        MyYAnimation myYAnimation = new MyYAnimation();
                        myYAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                Log.i(TAG, "onAnimationStart: ");
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                Log.i(TAG, "onAnimationEnd: ");
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                                Log.i(TAG, "onAnimationRepeat: ");
                            }
                        });
                        fancyRl.startAnimation(myYAnimation);
                    }
                    // 点击了当前ViewPager内部
                    Toast.makeText(getContext(), "点击了课程", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * @param ev
     * @return
     */
    private View viewOfClickOnScreen(MotionEvent ev) {
        int childCount = viewMap.size();
        int[] location = new int[2];
        for (int i = 0; i < childCount; i++) {
            View v = viewMap.get(i);
            v.getLocationOnScreen(location);

            Log.i(TAG, "viewOfClickOnScreen: " + i + ": " + location[0]);
            int minX = location[0];
            int minY = getTop();

            int maxX = location[0] + v.getWidth() - 50;
            int maxY = getBottom();

            float x;
            if (ev.getX() != ev.getRawX()) {
                x = ev.getX() + getX();
            } else {
                x = ev.getX();
            }
            float y = ev.getY();

            if (minX != 0 && (x > minX && x < maxX) && (y > minY && y < maxY)) {
                currentItem = i;
                return v;
            }
        }
        return null;
    }
}  