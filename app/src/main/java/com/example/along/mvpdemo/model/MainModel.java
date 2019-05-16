package com.example.along.mvpdemo.model;

import android.content.Context;

import com.example.along.mvpdemo.base.BaseEntity;
import com.example.along.mvpdemo.model.bean.AreaCodeBean;
import com.example.along.mvpdemo.model.bean.AreaCodeParams;
import com.example.along.mvpdemo.model.bean.LanguageBean;
import com.example.along.mvpdemo.rx.BaseObserver;
import com.example.along.mvpdemo.rx.RetrofitFactory;
import com.example.along.mvpdemo.rx.RxHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by long on 18-4-23.
 */

public class MainModel implements IMainModel {

    @Override
    public void getLanguage(Context context, String lang, final OnGetLanguageListener onGetLanguageListener) {
        Map<String, String> map = new HashMap<>();
        map.put("lang",lang);
        RetrofitFactory.getInstance(context).API().getlanguage(map)
                .compose(RxHelper.<BaseEntity<List<LanguageBean>>>io_main())
                .subscribe(new BaseObserver<List<LanguageBean>>(context) {
                    @Override
                    public void onSuccess(List<LanguageBean> languageBeanList, String msg) {
                        if (onGetLanguageListener != null) {
                            onGetLanguageListener.onGetLanguageSuccess(languageBeanList);
                        }
                    }

                    @Override
                    protected void onFailure(String msg) {
                        if (onGetLanguageListener != null) {
                            onGetLanguageListener.onGetLanguageError(msg);
                        }
                    }
                });
    }
}
