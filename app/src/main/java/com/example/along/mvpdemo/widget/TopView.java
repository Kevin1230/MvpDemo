package com.example.along.mvpdemo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.along.mvpdemo.R;


public class TopView extends RelativeLayout {
    
    private Context mContext;
    private View mRootView;
    private ImageView mBackIv;//返回按钮
    private TextView mContentTv;//中间内容
    private TextView tvHelp;//中间内容
    private RelativeLayout rootView;
    private LinearLayout layoutBack;//返回点击区域
    
    public TopView(Context context) {
        this(context, null);
    }
    
    public TopView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public TopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }
    
    private void initView() {
        mRootView = inflate(mContext, R.layout.layout_top_view, this);
        mBackIv = mRootView.findViewById(R.id.iv_back);
        mContentTv = mRootView.findViewById(R.id.tv_content);
        rootView = mRootView.findViewById(R.id.root_view);
        layoutBack = mRootView.findViewById(R.id.layout_back);
        tvHelp = mRootView.findViewById(R.id.tv_help);
    }
    
    /**
     * 是否显示左边的返回键
     *
     * @param leftVisible true 显示
     * @author caiyangbin
     * create at 2017/7/21 下午3:08
     */
    
    public void setLeftVisibility(boolean leftVisible) {
        if (leftVisible) {
            layoutBack.setVisibility(View.VISIBLE);
        } else {
            layoutBack.setVisibility(View.GONE);
        }
    }
    
    
    public void setRootBackgroundColor(int color) {
        rootView.setBackgroundResource(color);
    }
    
    public void setLeftBg(int resId) {
        mBackIv.setImageResource(resId);
    }
    
    /**
     * 设置显示内容
     *
     * @param context 显示内容
     * @return
     * @author caiyangbin
     * create at 2017/7/21 下午3:09
     */
    public void setContent(String context) {
        mContentTv.setText(context);
    }
    
    /**
     * 设置左边返回键点击事件
     *
     * @param l listener
     * @author caiyangbin
     * create at 2017/7/21 下午3:11
     */
    public void setLeftClick(OnClickListener l) {
        layoutBack.setOnClickListener(l);
    }
    
    /**
     * 设置内容文字颜色
     *
     * @param color 颜色值
     * @return
     * @author caiyangbin
     * create at 2017/7/28 上午9:51
     */
    public void setContentTextColor(int color) {
        mContentTv.setTextColor(color);
    }
    
    
    public void setRightClick(OnClickListener l) {
        tvHelp.setOnClickListener(l);
    }
    
    public void setRightShow(boolean isShow) {
        if (isShow) {
            tvHelp.setVisibility(VISIBLE);
        } else {
            tvHelp.setVisibility(GONE);
        }
    }
    
    public void setRightContent(String text) {
        tvHelp.setText(text);
    }
    
    public void setRightContentColor(int colorId) {
        tvHelp.setTextColor(mContext.getResources().getColor(colorId));
    }
    
}
