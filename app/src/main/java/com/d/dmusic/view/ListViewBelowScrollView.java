package com.d.dmusic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/*
 * ListViewBelowScrollView类继承自ListView
 * 解决问题：ListView布局为ScrollView子布局时，会出现只显示一行子控件并失去宽度自适应的情况，
 * */
public class ListViewBelowScrollView extends ListView {

	public ListViewBelowScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ListViewBelowScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ListViewBelowScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
