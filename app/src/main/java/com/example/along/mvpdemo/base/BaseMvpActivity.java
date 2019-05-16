package com.example.along.mvpdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.example.along.mvpdemo.constant.Constants;
import com.example.along.mvpdemo.model.event.EventMessage;
import com.example.along.mvpdemo.utils.ReflectUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 说明: Activity基类 <br/>
 * Create at 2017/12/21-下午10:47 by long
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    
    protected P mPresenter;
    protected Context mContext;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;
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
        
        ButterKnife.bind(this);
        
        initView();
        initData();
        initEvent();
    }
    
    protected abstract int getLayoutId();
    
    protected abstract void initView();
    
    protected abstract void initEvent();
    
    /**
     * 初始化数据,默认在onCreate中调用
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
        //  ToastUtil.showToast(mContext, msg);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        if (EventBus.getDefault().isRegistered(this)) {
            //已注册注销
            EventBus.getDefault().unregister(this);
        }
        
        if (null != mPresenter) {
            mPresenter.onDetach();
        }
    }
    //双击退出相关
    //  private boolean mFlag = false;
    //  private long mTimeout = -1;
    //
    //  private boolean checkBackAction() {
    //    long time = 3000L;//判定时间设为3秒
    //    boolean flag = mFlag;
    //    mFlag = true;
    //    boolean timeout = (mTimeout == -1 || (System.currentTimeMillis() - mTimeout) > time);
    //    if (mFlag && (mFlag != flag || timeout)) {
    //      mTimeout = System.currentTimeMillis();
    //      ToastUtils.showShort("再点击一次回到桌面");
    //      return true;
    //    }
    //    return !mFlag;
    //  }
    
    //  @Override
    //  public boolean onKeyDown(int keyCode, KeyEvent event) {
    //    return checkBackAction() || super.onKeyDown(keyCode, event);
    //  }
    
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(EventMessage event) {
    }
    
    public static void noLogin() {
        SPUtils.getInstance(Constants.SPConstants.H5_INFO_KEY).remove("token");
        SPUtils.getInstance(Constants.SPConstants.H5_INFO_KEY).remove("gesturePassword");
        SPUtils.getInstance(Constants.SPConstants.H5_INFO_KEY).remove("userId");
        SPUtils.getInstance(Constants.SPConstants.H5_INFO_KEY).remove("userInfo");
        EventMessage<String> objectEventMessage = new EventMessage<>(Constants.EventBusConstants.NO_LOGIN, "");
        EventBus.getDefault().post(objectEventMessage);
    }
}