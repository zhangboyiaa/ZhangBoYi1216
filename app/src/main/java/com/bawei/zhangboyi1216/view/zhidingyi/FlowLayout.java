package com.bawei.zhangboyi1216.view.zhidingyi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.zhangboyi1216.R;

/**
 * date:2019/12/16
 * author:张博一(zhangboyi)
 * function:FLOW布局
 */
public class FlowLayout extends ViewGroup {

    int wid = 0;
    private int color;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wid = displayMetrics.widthPixels;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        color = typedArray.getColor(R.styleable.FlowLayout_myColor, Color.RED);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int w = 30;
        int h = 20;
        int left = w;
        int top = h;
        int right = 0;
        int bottom = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++){
            View childAt = getChildAt(i);
            childAt.measure(0,0);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            right = left + measuredWidth;
            if (right<wid){
                bottom = top + measuredHeight;
            }else {
                left = w;
                top = h;
                right = left + measuredWidth;
                bottom = top + measuredHeight;
            }
            childAt.layout(left,top,right,bottom);
            right = left+w;
        }
    }

    public void addTag(final String tag){
        final TextView textView = new TextView(getContext());
        textView.setText(tag);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTagClickListener != null) {
                    onTagClickListener.onTagClick(textView.getText().toString());
                }
            }
        });
        textView.setTextColor(color);
        addView(textView);

    }

    OnTagClickListener onTagClickListener;

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }

    public interface OnTagClickListener{
        void onTagClick(String tag);
    }
}
