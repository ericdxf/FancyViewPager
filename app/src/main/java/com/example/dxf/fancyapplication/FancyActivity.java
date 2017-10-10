package com.example.dxf.fancyapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FancyActivity extends AppCompatActivity {
    private static final String TAG = "FancyActivity";
    private ClipViewPager courseVp;
    private List<ImageView> mShufImages;
    private CoursePagerAdapter mShufAdapter;
    private RelativeLayout outRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fancy);
        initWidget();
        courseVp = (ClipViewPager) findViewById(R.id.choose_course_vp);
        outRl = (RelativeLayout) findViewById(R.id.rl_home_shuf);
        initVp();
    }

    private void initVp() {
        List<String> courseList = new ArrayList<>();
        courseList.add("中级会计实务");
        courseList.add("中级会计实务");
        courseList.add("中级会计实务");
        courseList.add("中级会计实务");
        courseList.add("中级会计实务");
        courseList.add("中级会计实务");
        courseList.add("中级会计实务");
        courseList.add("中级会计实务");
        courseList.add("中级会计实务");
        courseList.add("中级会计实务");
        courseList.add("中级会计实务中级会计实务中级会计实务中级");
        courseList.add("中级会计实务中级会计实务中级会计实务中级");
        courseList.add("中级会计实务中级会计实务中级会计实务中级");
        courseList.add("中级会计实务中级会计实务中级会计实务中级");
        courseList.add("中级会计实务中级会计实务中级会计实务中级");
        courseList.add("中级会计实务中级会计实务中级会计实务中级");
        courseList.add("中级会计实务中级会计实务中级会计实务中级");
        courseList.add("中级会计实务中级会计实务中级会计实务中级");
        courseList.add("中级会计实务中级会计实务中级会计实务中级");
        courseList.add("中级会计实务中级会计实务中级会计实务中级");
        courseList.add("中级会计实务中级会计实务中级会计实务中级");

        mShufAdapter = new CoursePagerAdapter(this, courseList);
        mShufAdapter.setOnPageSelectListener(new CoursePagerAdapter.OnPageSelectListener() {
            @Override
            public void select(int position) {
                Log.i(TAG, "select: " + position);
            }
        });

        courseVp.setAdapter(mShufAdapter);
        courseVp.addOnPageChangeListener(mShufAdapter);
        courseVp.setOffscreenPageLimit(9);
        courseVp.setPageMargin(-100);
        courseVp.setCurrentItem(2);
        courseVp.setOnCourseClickedListener(new ClipViewPager.onCourseClickedListener() {
            @Override
            public void onCourseClicked(int leftMargin, int topMargin, int width, int height, int item, String name) {
                Toast.makeText(FancyActivity.this, "点击了课程123", Toast.LENGTH_SHORT).show();
                int[] location = new int[2];
                outRl.getLocationOnScreen(location);

                final View showView = LayoutInflater.from(FancyActivity.this).inflate(R.layout.item_course, null);
                // 建立layout属性对象
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(0, 0);
                // 给副本View设置位置，与目标View重合
                params.leftMargin = leftMargin;
                params.topMargin = topMargin - location[1];
                // 这里还需要设置大小等属性
                params.width = width;
                params.height = height;
                showView.setLayoutParams(params);
                // 加入父View中
                outRl.addView(showView, params);

                RelativeLayout fancyRl = (RelativeLayout) showView.findViewById(R.id.item_fancy_rl);
                if (item % 3 == 0) {
                    fancyRl.setBackgroundResource(R.drawable.bg_book_blue);
                } else if (item % 3 == 1) {
                    fancyRl.setBackgroundResource(R.drawable.bg_book_yellow);
                } else {
                    fancyRl.setBackgroundResource(R.drawable.bg_book_white);
                }
                TextView courseNameTv = (TextView) showView.findViewById(R.id.item_fancy_tv);
                courseNameTv.setText(name);
                courseNameTv.setTextSize(12);

                MyYAnimation myYAnimation = new MyYAnimation();
                myYAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.i(TAG, "onAnimationStart: ");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.i(TAG, "onAnimationEnd: ");
                        outRl.removeView(showView);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.i(TAG, "onAnimationRepeat: ");
                    }
                });
                showView.startAnimation(myYAnimation);
            }
        });

        outRl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return courseVp.dispatchTouchEvent(event);
            }
        });
    }

    public void initWidget() {

    }

}
