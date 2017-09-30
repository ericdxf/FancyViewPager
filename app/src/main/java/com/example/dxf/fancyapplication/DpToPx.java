package com.example.dxf.fancyapplication;

import android.content.Context;

public class DpToPx {
	/**
	 * 将dp转换成像素
	 * 
	 * @param dp
	 * @return
	 */
	public static int Dp2Px(Context context, float dp) {
		if (context != null) {
			final float scale = context.getResources().getDisplayMetrics().density;
			return (int) (dp * scale + 0.5f);
		} else {
			return (int) (dp * 3);
		}
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int Px2Dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
