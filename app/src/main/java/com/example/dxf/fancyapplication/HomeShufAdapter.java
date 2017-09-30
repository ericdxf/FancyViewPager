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
    public static int sHeightPadding = DimenUtils.dp2px(16);
    public static int sTopPadding = DimenUtils.dp2px(32);
    public static int sBottomPadding = DimenUtils.dp2px(0);
    private final Context context;
    private int lastPosition;
    // 缓存列表
//    LinkedList<View> mCaches = new LinkedList<>();
    HashMap<Integer, View> viewMap = new HashMap<>();
//    List<View> mCaches = new ArrayList<>();

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
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView;
//        if (position == 1 || position == 3) {
//            imgView.setPadding(sWidthPadding, -sHeightPadding, sWidthPadding, sHeightPadding *2);
//        }
//        if (position == 2) {
//            imgView.setPadding(sWidthPadding, 0, sWidthPadding, sHeightPadding *2);
//        }

//        ImageView imgView = courseList.get(position);
//        container.addView(imgView);
//        return imgView;

//        ViewHolder mHolder;
//        if (mCaches.size() < 10) {
//            convertView = View.inflate(context, R.layout.item_course_fancy, null);
//            convertView.setPadding(HomeShufAdapter.sWidthPadding, HomeShufAdapter.sHeightPadding, HomeShufAdapter.sWidthPadding, HomeShufAdapter.sHeightPadding);
//            mHolder = new ViewHolder();
//            mHolder.courseNameTv = (TextView) convertView.findViewById(R.id.item_fancy_tv);
//            convertView.setTag(mHolder);
//            mCaches.add(convertView);
//        } else {
//            convertView = mCaches.removeFirst();
//            mHolder = (ViewHolder) convertView.getTag();
//        }
//        mHolder.courseNameTv.setText("会计实务" + position);
//        container.addView(convertView);

        Log.i(TAG, "instantiateItem: " + position);
        convertView = View.inflate(context, R.layout.item_course_fancy, null);
        convertView.setPadding(HomeShufAdapter.sWidthPadding, HomeShufAdapter.sTopPadding, HomeShufAdapter.sWidthPadding, HomeShufAdapter.sBottomPadding);
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
            //当前手指触摸滑动的页面,从0页滑动到1页 offset越来越大，padding越来越大
//            int outWidthPadding = (int) (positionOffset * sWidthPadding);
//            int outTopPadding = (int) (positionOffset * sTopPadding);
//            int outBottomPadding = (int) ((1 - positionOffset) * sTopPadding);
//            viewMap.get(position).setPadding(outWidthPadding, outTopPadding, outWidthPadding, outBottomPadding);
//            Log.i(TAG, "onPageScrolled: " + position + "   " + positionOffset + "   " + outTopPadding + "   " + outBottomPadding);
//
//            if (position < viewMap.size() - 1) {
//                int inWidthPadding = (int) ((1 - positionOffset) * sWidthPadding);
//                int inTopPadding = (int) ((1 - positionOffset) * sTopPadding);
//                int inBottomPadding = (int) ((positionOffset) * sTopPadding);
//                viewMap.get(position + 1).setPadding(inWidthPadding, inTopPadding, inWidthPadding, inBottomPadding);
//            }


            if (position - 1 >= 0) {
                int outWidthPadding = (int) ((positionOffset + 1) * sWidthPadding / 2);
                int outTopPadding = (int) ((1 + positionOffset) * sTopPadding / 2);
                int outBottomPadding = (int) ((1 - positionOffset) * sTopPadding / 2);
                viewMap.get(position - 1).setPadding(outWidthPadding, outTopPadding, outWidthPadding, outBottomPadding);
            }

            {
                int outWidthPadding = (int) (positionOffset * sWidthPadding / 2);
                int outTopPadding = (int) ((positionOffset) * sTopPadding / 2);
                int outBottomPadding = (int) ((2 - positionOffset) * sTopPadding / 2);
                viewMap.get(position).setPadding(outWidthPadding, outTopPadding, outWidthPadding, outBottomPadding);
                Log.i(TAG, "onPageScrolled: " + position + "   " + positionOffset + "   " + outTopPadding + "   " + outBottomPadding);
            }

            if (position < viewMap.size() - 1) {
                int inWidthPadding = (int) ((1 - positionOffset) * sWidthPadding / 2);
                int inTopPadding = (int) ((1 - positionOffset) * sTopPadding / 2);
                int inBottomPadding = (int) ((1 + positionOffset) * sTopPadding / 2);
                viewMap.get(position + 1).setPadding(inWidthPadding, inTopPadding, inWidthPadding, inBottomPadding);
            }

            if (position < viewMap.size() - 2) {
                int inWidthPadding = (int) ((2 - positionOffset) * sWidthPadding / 2);
                int inTopPadding = (int) ((2 - positionOffset) * sTopPadding / 2);
                int inBottomPadding = (int) ((positionOffset) * sTopPadding / 2);
                viewMap.get(position + 2).setPadding(inWidthPadding, inTopPadding, inWidthPadding, inBottomPadding);
            }

            // 因为Offset的变化存在跳跃现象，所以要把停止移动的控件位置复原
            if (lastPosition != position) {
                Log.i(TAG, "onPageScrolled: setpadding" + lastPosition);
                if (position > lastPosition) {
                    // 向右滑动
                    if (lastPosition - 1 > 0) {
                        viewMap.get(lastPosition - 1).setPadding(sWidthPadding, sTopPadding, sWidthPadding, sBottomPadding);
                    }
                } else {
                    // 向左滑动
                    if (lastPosition + 2 < viewMap.size()) {
                        viewMap.get(lastPosition + 2).setPadding(sWidthPadding, sTopPadding, sWidthPadding, sBottomPadding);
                    }
                }
                lastPosition = position;
            }

//
//            // 因为Offset的变化存在跳跃现象，所以要把停止移动的控件位置复原
//            if (lastPosition != position) {
//                Log.i(TAG, "onPageScrolled: setpadding" + lastPosition);
//                if (position > lastPosition) {
//                    viewMap.get(lastPosition).setPadding(sWidthPadding, sTopPadding, sWidthPadding, sBottomPadding);
//                } else {
//                    if (lastPosition + 1 < viewMap.size()) {
//                        viewMap.get(lastPosition + 1).setPadding(sWidthPadding, sTopPadding, sWidthPadding, sBottomPadding);
//                    }
//                }
//                lastPosition = position;
//            }

            viewMap.get((positionOffset > 0.5 ? 1 : 0) + position).bringToFront();
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

    private class ViewHolder {
        TextView courseNameTv;
    }
}
