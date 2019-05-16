package com.example.along.mvpdemo.contract;

import android.content.Context;

import com.example.along.mvpdemo.base.BasePresenter;
import com.example.along.mvpdemo.base.IBaseView;
import com.example.along.mvpdemo.model.bean.AreaCodeBean;
import com.example.along.mvpdemo.model.bean.LanguageBean;

import java.util.List;


/**
 * 说明:  <br/>
 * Create at 2018/3/14--11:17 by long
 */
public interface MainContract {

  interface View extends IBaseView {
    void onGetLanguageSuccess(List<LanguageBean> languageBeanList);
    void onGetLanguageFail(String errMsg);
  }

  abstract class Presenter extends BasePresenter<View> {
    public abstract void onGetLanguage(Context context,String lang);
  }
}
