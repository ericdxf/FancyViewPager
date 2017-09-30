package com.example.dxf.fancyapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dxf.fancyapplication.FancyCoverFlow.FancyCoverFlow;

import java.util.ArrayList;
import java.util.List;

public class FancyActivity extends AppCompatActivity {

    private FancyCoverFlow fancyView;
    private CourseFancyAdapter fancyAdapter;
    private ViewPager courseVp;
    private List<ImageView> mShufImages;
    private HomeShufAdapter mShufAdapter;
    private RelativeLayout outRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fancy);
        fancyView = (FancyCoverFlow) findViewById(R.id.choose_course_fancyCoverFlow);
        initWidget();
        courseVp = (ViewPager) findViewById(R.id.choose_course_vp);
        outRl = (RelativeLayout) findViewById(R.id.rl_home_shuf);
        initVp();
    }

    private void initVp() {
//        mShufImages = new ArrayList<>();
//        ImageView imageView1 = new ImageView(this);
//        imageView1.setPadding(HomeShufAdapter.sWidthPadding, HomeShufAdapter.sHeightPadding,HomeShufAdapter.sWidthPadding,HomeShufAdapter.sHeightPadding);
//        imageView1.setImageResource(R.drawable.bg_book_blue);
//        ImageView imageView2 = new ImageView(this);
//        imageView2.setPadding(HomeShufAdapter.sWidthPadding, HomeShufAdapter.sHeightPadding,HomeShufAdapter.sWidthPadding,HomeShufAdapter.sHeightPadding);
//        imageView2.setImageResource(R.drawable.bg_book_white);
//        ImageView imageView3 = new ImageView(this);
//        imageView3.setPadding(HomeShufAdapter.sWidthPadding, HomeShufAdapter.sHeightPadding,HomeShufAdapter.sWidthPadding,HomeShufAdapter.sHeightPadding);
//        imageView3.setImageResource(R.drawable.bg_book_yellow);
//        ImageView imageView4 = new ImageView(this);
//        imageView4.setPadding(HomeShufAdapter.sWidthPadding, HomeShufAdapter.sHeightPadding,HomeShufAdapter.sWidthPadding,HomeShufAdapter.sHeightPadding);
//        imageView4.setImageResource(R.drawable.bg_book_blue);
//        ImageView imageView5 = new ImageView(this);
//        imageView5.setPadding(HomeShufAdapter.sWidthPadding, HomeShufAdapter.sHeightPadding,HomeShufAdapter.sWidthPadding,HomeShufAdapter.sHeightPadding);
//        imageView5.setImageResource(R.drawable.bg_book_white);
//        ImageView imageView6 = new ImageView(this);
//        imageView6.setPadding(HomeShufAdapter.sWidthPadding, HomeShufAdapter.sHeightPadding,HomeShufAdapter.sWidthPadding,HomeShufAdapter.sHeightPadding);
//        imageView6.setImageResource(R.drawable.bg_book_yellow);
//        mShufImages.add(imageView1);
//        mShufImages.add(imageView2);
//        mShufImages.add(imageView3);
//        mShufImages.add(imageView4);
//        mShufImages.add(imageView5);
//        mShufImages.add(imageView6);

        List<String> courseList = new ArrayList<>();
        courseList.add("中级1");
        courseList.add("中级2");
        courseList.add("中级3");
        courseList.add("中级4");
        courseList.add("中级5");
        courseList.add("中级6");
        courseList.add("中级1");
        courseList.add("中级2");
        courseList.add("中级3");
        courseList.add("中级4");
        courseList.add("中级5");
        courseList.add("中级6");
        courseList.add("中级1");
        courseList.add("中级2");
        courseList.add("中级3");
        courseList.add("中级4");
        courseList.add("中级5");
        courseList.add("中级6");
        courseList.add("中级1");
        courseList.add("中级2");
        courseList.add("中级3");
        courseList.add("中级4");
        courseList.add("中级5");
        courseList.add("中级6");

        mShufAdapter = new HomeShufAdapter(this, courseList);
        mShufAdapter.setOnPageSelectListener(new HomeShufAdapter.OnPageSelectListener() {
            @Override
            public void select(int position) {
//                courseVp.bringToFront(position);

            }
        });

        courseVp.setAdapter(mShufAdapter);
        courseVp.addOnPageChangeListener(mShufAdapter);
        courseVp.setOffscreenPageLimit(9);
        courseVp.setPageMargin(-100);
        courseVp.setCurrentItem(2);

        outRl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return courseVp.dispatchTouchEvent(event);
            }
        });
    }

    public void initWidget() {
        fancyView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        fancyView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<String> list = new ArrayList<>();
        list.add("中级会计实务");
        list.add("中级会计实务");
        list.add("中级会计实务");
        list.add("中级会计实务");
        list.add("中级会计实务");
        list.add("中级会计实务");
        list.add("中级会计实务");
        setFancyAdapter(list);
    }

    /**
     * @date 创建时间: 2016/12/6
     * @author XiaoFeng
     * @Description 设置适配器显示
     */
    private void setFancyAdapter(List<String> list) {
        // 设置滚动数据
        fancyAdapter = new CourseFancyAdapter(this, fancyView, list);
        fancyView.setAdapter(fancyAdapter);
        fancyView.setCourseStyle();
        // 设置fancyView循环显示
        fancyView.setSelection(0);
        fancyAdapter.setItemOnClick(new CourseFancyAdapter.ItemOnClick() {
            @Override
            public void myClick(String institutionModel) {

            }
        });
    }

}
