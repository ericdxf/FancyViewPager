package com.example.dxf.fancyapplication;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;


/**
 * 首页轮播图
 * Created by mdw on 2016/4/20.
 */
public class HomeShufAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
    private static final String TAG = "HomeShufAdapter";
    public static int sWidthPadding = DimenUtils.dp2px(12);
    public static int sTopPadding = DimenUtils.dp2px(60);
    public static int sBottomPadding = DimenUtils.dp2px(32);
    private final Context context;
    private int lastPosition;
    // 缓存列表
    HashMap<Integer, View> viewMap = new HashMap<>();

    private List<String> courseList;

    private OnPageSelectListener listener;

    public HomeShufAdapter(Context context, List<String> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewMap.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View convertView;
        Log.i(TAG, "instantiateItem: " + position);
        convertView = View.inflate(context, R.layout.item_course_fancy, null);
        RelativeLayout outRl = (RelativeLayout) convertView.findViewById(R.id.item_fancy_out_rl);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) outRl.getLayoutParams();
        lp.setMargins(HomeShufAdapter.sWidthPadding, HomeShufAdapter.sTopPadding, HomeShufAdapter.sWidthPadding, 0);

        RelativeLayout fancyRl = (RelativeLayout) convertView.findViewById(R.id.item_fancy_rl);
        if (position % 3 == 0) {
            fancyRl.setBackgroundResource(R.drawable.bg_book_blue);
        } else if (position % 3 == 1) {
            fancyRl.setBackgroundResource(R.drawable.bg_book_yellow);
        } else {
            fancyRl.setBackgroundResource(R.drawable.bg_book_white);
        }
        TextView courseNameTv = (TextView) convertView.findViewById(R.id.item_fancy_tv);
        courseNameTv.setText(courseList.get(position));
        viewMap.put(position, convertView);
        convertView.setTag(position);
//        mCaches.add(convertView);
        container.addView(convertView);
        return convertView;
    }

    @Override
    public int getCount() {
        return courseList == null ? 0 : courseList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (viewMap.size() > 0 && position < viewMap.size()) {
            int textSizeOffect = 3;
            // 当前手指触摸滑动的页面,从0页滑动到1页 offset越来越大，padding越来越大
            if (position - 1 >= 0) {
                int outWidthPadding = (int) ((positionOffset + 1) * sWidthPadding / 2);
                int outTopPadding = (int) ((1 + positionOffset) * sTopPadding / 2);
                int outBottomPadding = (int) ((1 - positionOffset) * sBottomPadding / 2);
//                viewMap.get(position - 1).setPadding(outWidthPadding, outTopPadding, outWidthPadding, outBottomPadding);

                RelativeLayout outRl = (RelativeLayout) viewMap.get(position - 1).findViewById(R.id.item_fancy_out_rl);
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) outRl.getLayoutParams();
                lp.setMargins(outWidthPadding, outTopPadding, outWidthPadding, outBottomPadding);

                TextView courseNameTv = (TextView) viewMap.get(position - 1).findViewById(R.id.item_fancy_tv);
                courseNameTv.setTextSize(12 - textSizeOffect * (positionOffset + 1) / 2);
            }

            {
                int outWidthPadding = (int) (positionOffset * sWidthPadding / 2);
                int outTopPadding = (int) ((positionOffset) * sTopPadding / 2);
                int outBottomPadding = (int) ((2 - positionOffset) * sBottomPadding / 2);
//                viewMap.get(position).setPadding(outWidthPadding, outTopPadding, outWidthPadding, outBottomPadding);

                RelativeLayout outRl = (RelativeLayout) viewMap.get(position).findViewById(R.id.item_fancy_out_rl);
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) outRl.getLayoutParams();
                lp.setMargins(outWidthPadding, outTopPadding, outWidthPadding, outBottomPadding);

                TextView courseNameTv = (TextView) viewMap.get(position).findViewById(R.id.item_fancy_tv);
                courseNameTv.setTextSize(12 - textSizeOffect * positionOffset / 2);
            }

            if (position < viewMap.size() - 1) {
                int inWidthPadding = (int) ((1 - positionOffset) * sWidthPadding / 2);
                int inTopPadding = (int) ((1 - positionOffset) * sTopPadding / 2);
                int inBottomPadding = (int) ((1 + positionOffset) * sBottomPadding / 2);
//                viewMap.get(position + 1).setPadding(inWidthPadding, inTopPadding, inWidthPadding, inBottomPadding);

                RelativeLayout outRl = (RelativeLayout) viewMap.get(position + 1).findViewById(R.id.item_fancy_out_rl);
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) outRl.getLayoutParams();
                lp.setMargins(inWidthPadding, inTopPadding, inWidthPadding, inBottomPadding);

                TextView courseNameTv = (TextView) viewMap.get(position + 1).findViewById(R.id.item_fancy_tv);
                courseNameTv.setTextSize(12 - textSizeOffect * (1 - positionOffset) / 2);
            }

            if (position < viewMap.size() - 2) {
                int inWidthPadding = (int) ((2 - positionOffset) * sWidthPadding / 2);
                int inTopPadding = (int) ((2 - positionOffset) * sTopPadding / 2);
                int inBottomPadding = (int) ((positionOffset) * sBottomPadding / 2);
//                viewMap.get(position + 2).setPadding(inWidthPadding, inTopPadding, inWidthPadding, inBottomPadding);

                RelativeLayout outRl = (RelativeLayout) viewMap.get(position + 2).findViewById(R.id.item_fancy_out_rl);
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) outRl.getLayoutParams();
                lp.setMargins(inWidthPadding, inTopPadding, inWidthPadding, inBottomPadding);

                TextView courseNameTv = (TextView) viewMap.get(position + 2).findViewById(R.id.item_fancy_tv);
                courseNameTv.setTextSize(12 - textSizeOffect * (2 - positionOffset) / 2);
            }

            // 因为Offset的变化存在跳跃现象，所以要把停止移动的控件位置复原
            if (lastPosition != position) {
                if (position > lastPosition) {
                    // 向右滑动
                    if (lastPosition - 1 >= 0) {
                        viewMap.get(lastPosition - 1).setPadding(sWidthPadding, sTopPadding, sWidthPadding, 0);
                    }
                } else {
                    // 向左滑动
                    if (lastPosition + 2 < viewMap.size()) {
                        viewMap.get(lastPosition + 2).setPadding(sWidthPadding, sTopPadding, sWidthPadding, 0);
                    }
                }
                lastPosition = position;
            }

            // 按顺序显示单项到顶部
            Log.i(TAG, "onPageScrolled position：" + position + " lastPosition：" + lastPosition);
            int realPosition = (positionOffset > 0.5 ? 1 : 0) + position;
            if (realPosition - 2 >= 0) {
                viewMap.get(realPosition - 2).bringToFront();
            }
            if (realPosition + 2 < viewMap.size()) {
                viewMap.get(realPosition + 2).bringToFront();
            }
            if (realPosition - 1 >= 0) {
                viewMap.get(realPosition - 1).bringToFront();
            }
            if (realPosition + 1 < viewMap.size()) {
                viewMap.get(realPosition + 1).bringToFront();
            }
            viewMap.get(realPosition).bringToFront();
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (listener != null) {
            listener.select(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setOnPageSelectListener(OnPageSelectListener listener) {
        this.listener = listener;
    }

    public interface OnPageSelectListener {
        void select(int position);
    }

    public HashMap<Integer, View> getViewMap() {
        return viewMap;
    }

    private class ViewHolder {
        TextView courseNameTv;
    }
}
