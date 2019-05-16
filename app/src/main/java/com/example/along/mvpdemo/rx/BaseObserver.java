package com.example.along.mvpdemo.rx;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.along.mvpdemo.R;
import com.example.along.mvpdemo.base.BaseEntity;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author long
 * @date 2018/12/3
 * @description 订阅的基类。
 */

public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {
    protected Context mContext;
    protected boolean isShowLoading;
    protected boolean showToast;

    //    public BaseObserver3() {
//    }
    public BaseObserver(Context context) {
        mContext = context;
    }

    public BaseObserver(Context context, boolean showToast) {
        mContext = context;
        this.showToast = showToast;
    }
    public BaseObserver(Context context, boolean isShowLoading, boolean showToast) {//默认showToast为flase  即toast提示错误信息
        mContext=context;
        this.isShowLoading = isShowLoading;
        this.showToast=showToast;
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {


        // onRequestEnd();
        Log.v("aaa",tBaseEntity.getCode()+"====="+tBaseEntity.getMsg());
        switch (tBaseEntity.getCode()) {
            case BaseEntity.CODE://成功
                onSuccess(tBaseEntity.getData(), tBaseEntity.getMsg());
                break;
            case BaseEntity.LOGOUT_CODE:
                onFailure(tBaseEntity.getMsg());
                break;
            default:
                //请求失败
                if (tBaseEntity != null && tBaseEntity.getMsg() != null) {
                    if (tBaseEntity.getData() != null)
                    if (mContext != null && !showToast) {
                        Toast.makeText(mContext, "" + tBaseEntity.msg, Toast.LENGTH_SHORT).show();
                    }
                    onFailure(tBaseEntity.getMsg());
                }
                break;
        }
    }




    private static final String TAG = "BaseObserver";

    @Override
    public void onError(Throwable e) {


        Log.w(TAG, "onError: "+e.getMessage());
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                if (mContext != null) {
                    Toast.makeText(mContext, R.string.network_error, Toast.LENGTH_SHORT).show();
                }
                onFailure(mContext.getString(R.string.network_fail));
            }  else {
                if (e instanceof IllegalStateException){
                    Toast.makeText(mContext,R.string.json_error,Toast.LENGTH_SHORT).show();
                }else if (e instanceof SocketTimeoutException){
                    Toast.makeText(mContext,R.string.network_timeout,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, R.string.server_error, Toast.LENGTH_SHORT).show();
                }
                onFailure("" + e.getMessage());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 返回成功
     *
     * @param
     * @throws Exception
     */
    public abstract void onSuccess(T data, String msg);

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected void onCodeError(BaseEntity<T> t) throws Exception {
    }

    /**
     * 返回失败
     *
     * @param
     * @throws Exception
     */
    protected abstract void onFailure(String msg);

}
