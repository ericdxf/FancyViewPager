package com.example.dxf.fancyapplication;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

public class ClipViewPager extends ViewPager {
    private static final String TAG = "ClipViewPager";
    private HashMap<Integer, View> viewMap = new HashMap<>();
    private float downX;
    private int currentItem;
    private onCourseClickedListener clickedListener;

    public ClipViewPager(Context context) {
        super(context);
    }

    public ClipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        CoursePagerAdapter mShufAdapter = (CoursePagerAdapter) getAdapter();
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
                    if (view != null && clickedListener != null) {
                        RelativeLayout fancyRl = (RelativeLayout) view.findViewById(R.id.item_fancy_rl);
                        TextView fancyTV = (TextView) view.findViewById(R.id.item_fancy_tv);
                        int[] location = new int[2];
                        fancyRl.getLocationOnScreen(location);
                        clickedListener.onCourseClicked(location[0], location[1], fancyRl.getWidth(), fancyRl.getHeight(), currentItem, fancyTV.getText().toString());
                    }
                    // 点击了当前ViewPager内部
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
//            int minY = getTop();

            int maxX = location[0] + v.getWidth();
//            int maxY = getBottom();

            float x;
            if (ev.getX() != ev.getRawX()) {
                x = ev.getX() + getX();
            } else {
                x = ev.getX();
            }
//            float y = ev.getY();

            if (minX != 0 && (x > minX && x < maxX)) {
                // 根据点击位置的是在左侧还是右侧，调整50的重叠部分
                if (i < currentItem) {
                    if (x < maxX - 50) {
                        currentItem = i;
                        return v;
                    }
                } else {
                    if (x < maxX + 50) {
                        currentItem = i;
                        return v;
                    }
                }
            }
        }
        return null;
    }

    public interface onCourseClickedListener {
        void onCourseClicked(int leftMargin, int rightMargin, int width, int height, int item, String name);
    }

    public void setOnCourseClickedListener(onCourseClickedListener clickedListener) {
        this.clickedListener = clickedListener;
    }
}  