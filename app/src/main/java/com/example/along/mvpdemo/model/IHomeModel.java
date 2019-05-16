package com.example.along.mvpdemo.model;

import android.content.Context;

import com.example.along.mvpdemo.model.bean.AreaCodeBean;

import java.util.List;


/**
 * Created by long on 18-4-23.
 */

public interface IHomeModel {
    void queryAvailableValunInfo(Context context, String keyword, String lang, OnQueryCustLmtListener onQueryCustLmtListener);

    interface OnQueryCustLmtListener{
        void onQueryAvailableValueInfoSuccess(List<AreaCodeBean> areaCodeBeanList);
        void onQueryAvailableValueInfoError(String errMsg);
    }

}
