package com.span.expressextension8868.utils.utility;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

public class ExpandableHeightListView extends ListView {

	boolean expanded = false;

	private ViewGroup.LayoutParams params;

	private int old_count = 0;

	public ExpandableHeightListView(Context context) {
		super(context);
	}

	public ExpandableHeightListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ExpandableHeightListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public boolean isExpanded() {
		return expanded;
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// HACK! TAKE THAT ANDROID!
		if (isExpanded()) {
			// Calculate entire height by providing a very large height hint.
			// But do not use the highest 2 bits of this integer; those are
			// reserved for the MeasureSpec mode.
			int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 1, MeasureSpec.AT_MOST);
			super.onMeasure(widthMeasureSpec, expandSpec);

			ViewGroup.LayoutParams params = getLayoutParams();
			/*params.height = getMeasuredHeight();*/
			
			
			params.height = 850;
			
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	/*
	 * @Override protected void onDraw(Canvas canvas) {
	 * 
	 * if (getCount() != old_count) {
	 * 
	 * old_count = getCount();
	 * 
	 * params = getLayoutParams();
	 * 
	 * params.height = getCount() * (old_count > 0 ? getChildAt(0).getHeight() +
	 * 1 : 0);
	 * 
	 * System.out.println("params h " + params.height + " " + params);
	 * 
	 * setLayoutParams(params); }
	 * 
	 * super.onDraw(canvas); }
	 */
}