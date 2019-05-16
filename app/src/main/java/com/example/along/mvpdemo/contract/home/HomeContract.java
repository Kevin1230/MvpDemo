package com.example.along.mvpdemo.contract.home;

import android.content.Context;

import com.example.along.mvpdemo.base.BasePresenter;
import com.example.along.mvpdemo.base.IBaseView;
import com.example.along.mvpdemo.model.bean.AreaCodeBean;

import java.util.List;


/**
 * 说明:  <br/>
 * Create at 2018/3/14--11:17 by long
 */
public interface HomeContract {

  interface View extends IBaseView {
    void onQueryAvailableValueInfoSuccess(List<AreaCodeBean> availableValueInfo);
    void onQueryAvailableValueInfoError(String errMsg);
  }

  abstract class Presenter extends BasePresenter<View> {
    public abstract void queryAvailableValunInfo(Context context,String keyword,String lang);
  }
}
