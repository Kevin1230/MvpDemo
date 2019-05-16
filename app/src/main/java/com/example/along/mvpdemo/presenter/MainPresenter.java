package com.example.along.mvpdemo.presenter;

import android.content.Context;

import com.example.along.mvpdemo.contract.MainContract;
import com.example.along.mvpdemo.contract.home.HomeContract;
import com.example.along.mvpdemo.model.HomeModel;
import com.example.along.mvpdemo.model.IHomeModel;
import com.example.along.mvpdemo.model.IMainModel;
import com.example.along.mvpdemo.model.MainModel;
import com.example.along.mvpdemo.model.bean.AreaCodeBean;
import com.example.along.mvpdemo.model.bean.LanguageBean;

import java.util.List;


/**
 * Created by long on 18-4-23.
 */

public class MainPresenter extends MainContract.Presenter {
    private MainModel mMainModel;

    @Override
    public void onGetLanguage(Context context, String lang) {
        if(mMainModel==null){
            mMainModel = new MainModel();
        }
        mMainModel.getLanguage(context,lang,new IMainModel.OnGetLanguageListener(){

            @Override
            public void onGetLanguageSuccess(List<LanguageBean> languageBeanList) {
                if(isViewAttached()){
                    getView().onGetLanguageSuccess(languageBeanList);
                }
            }

            @Override
            public void onGetLanguageError(String errMsg) {
                if(isViewAttached()){
                    getView().onGetLanguageFail(errMsg);
                }
            }
        });
    }
}
