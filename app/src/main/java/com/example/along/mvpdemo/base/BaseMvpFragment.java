package com.example.along.mvpdemo.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.along.mvpdemo.model.event.EventMessage;
import com.example.along.mvpdemo.utils.ReflectUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 说明: Fragment基类 <br/>
 * Create at 2017/12/21-下午10:47 by long
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends Fragment implements IBaseView {

    protected P mPresenter;
    protected View mRootView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mBind;

    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //初始化activity对象
        mActivity = getActivity();
        mContext = getContext();

        if (!EventBus.getDefault().isRegistered(this)) {
            //未注册才注册
            EventBus.getDefault().register(this);
        }

        if (isMvp()) {
            mPresenter = ReflectUtil.getT(this, 0);
            if (null == mPresenter) {
                throw new RuntimeException("请在实现类添加Presenter泛型....");
            }
            mPresenter.onAttach(this);
        }

        if(mRootView==null){
            mRootView = inflater.inflate(getLayoutResource(), container, false);
            mBind = ButterKnife.bind(this, mRootView);
            initView(mRootView);
        }
        initEvent();
        initData();

        return mRootView;
    }

    protected abstract int getLayoutResource();

    protected abstract void initView(View view);

    protected abstract void initEvent();

    /**
     * 初始化数据,默认在onCreateView中调用
     *
     * @author caiyangbin
     * create at 2017/7/24 下午2:54
     */
    protected abstract void initData();

    /**
     * 是否打开mvp模式
     *
     * @return true --打开 ，false --关闭
     * @author caiyangbin
     * create at 2017/7/23 上午11:06
     */
    protected abstract boolean isMvp();

    @Override
    public void showToast(String msg) {
       // ToastUtil.showToast(mContext, msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (EventBus.getDefault().isRegistered(this)) {
            //已注册注销
            EventBus.getDefault().unregister(this);
        }

        if (null != mPresenter) {
            mPresenter.onDetach();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(EventMessage event) {
    }
}
