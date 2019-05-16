package com.example.along.mvpdemo.ui.home;

import android.view.View;

import com.example.along.mvpdemo.R;
import com.example.along.mvpdemo.base.BaseMvpFragment;

public class TradeFragment extends BaseMvpFragment {
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_trade;
    }

    public static TradeFragment newInstance() {
        TradeFragment tradeFragment = new TradeFragment();
        return tradeFragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean isMvp() {
        return false;
    }
}
