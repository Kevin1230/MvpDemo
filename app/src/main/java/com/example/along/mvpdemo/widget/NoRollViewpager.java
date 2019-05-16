package com.example.along.mvpdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoRollViewpager extends LazyViewPager {
    public NoRollViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoRollViewpager(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
