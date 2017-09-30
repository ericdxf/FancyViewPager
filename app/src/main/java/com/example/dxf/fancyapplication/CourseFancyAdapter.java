package com.example.dxf.fancyapplication;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dxf.fancyapplication.FancyCoverFlow.FancyCoverFlow;
import com.example.dxf.fancyapplication.FancyCoverFlow.FancyCoverFlowAdapter;

import java.util.List;

/**
 * @date 创建时间: 2017/9/29
 * @author duxiaofeng
 * @Description 课程切换适配器
 */
public class CourseFancyAdapter extends FancyCoverFlowAdapter {
    private Context context;
    private FancyCoverFlow fancyCoverFlow;
    private int mItemWidth = 0;
    private int mItemHeight = 0;
    // 记录机构的列表
    List<String> industryList;

    private AnimationDrawable currentAnimDrawable;
    private Drawable defaultDrawable;

    private ItemOnClick itemOnClick;

    public CourseFancyAdapter(Context context, FancyCoverFlow fancyCoverFlow, List<String>
            industryList) {
        // 如果当前item正在播放，则设置设置播放动画
        this.context = context;
        this.fancyCoverFlow = fancyCoverFlow;
        this.industryList = industryList;
        mItemWidth = DpToPx.Dp2Px(context, 90);
        mItemHeight = DpToPx.Dp2Px(context, 130);
    }

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String getItem(int position) {
        return industryList.get(position % industryList.size());
//        return industryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 刷新指定item
     *
     * @param index    用于集合取数据
     * @param position item在listview中的位置
     */
    public void updateItem(int index, int position) {

        // 获取当前可以看到的item位置
        int visiblePosition = fancyCoverFlow.getFirstVisiblePosition();
        // 如添加headerview后 firstview就是hearderview
        // 所有索引+1 取第一个view
        // View view = listview.getChildAt(index - visiblePosition + 1);
        // 获取点击的view
        View view = fancyCoverFlow.getChildAt(position - visiblePosition);
    }

    @Override
    public View getCoverFlowItem(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_course_fancy, null);
            viewHolder = new ViewHolder();

            convertView.setLayoutParams(new FancyCoverFlow.LayoutParams(mItemWidth, mItemHeight));
            if (convertView != null) {
                convertView.setTag(viewHolder);
            }
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        ImageLoader.getInstance().displayImage(getItem(position).getOriginalImg(), viewHolder.topImg, options);
        return convertView;
    }

    class ViewHolder {
        ImageView topImg;
        TextView nameTv;
        TextView nameEnglishTv;
        TextView learnHourTv;
        TextView learnNumTv;
        Button checkBtn;
    }

    public interface ItemOnClick {
        void myClick(String institutionModel);
    }
}