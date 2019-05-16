package com.example.along.mvpdemo.model;

import android.content.Context;

import com.example.along.mvpdemo.model.bean.AreaCodeBean;
import com.example.along.mvpdemo.model.bean.LanguageBean;

import java.util.List;


/**
 * Created by long on 18-4-23.
 */

public interface IMainModel {
    void getLanguage(Context context,String lang, OnGetLanguageListener onGetLanguageListener);

    interface OnGetLanguageListener{
        void onGetLanguageSuccess(List<LanguageBean> languageBeanList);
        void onGetLanguageError(String errMsg);
    }

}
