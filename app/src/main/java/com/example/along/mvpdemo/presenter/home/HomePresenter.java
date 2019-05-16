package com.example.along.mvpdemo.presenter.home;

import android.content.Context;

import com.example.along.mvpdemo.contract.home.HomeContract;
import com.example.along.mvpdemo.model.HomeModel;
import com.example.along.mvpdemo.model.IHomeModel;
import com.example.along.mvpdemo.model.bean.AreaCodeBean;

import java.util.List;


/**
 * Created by long on 18-4-23.
 */

public class HomePresenter extends HomeContract.Presenter {
    private IHomeModel mHomeModel;
    @Override
    public void queryAvailableValunInfo(Context context, String keyword, String lang) {
        if(mHomeModel==null){
            mHomeModel = new HomeModel();
        }
        mHomeModel.queryAvailableValunInfo(context,keyword,lang,new IHomeModel.OnQueryCustLmtListener() {
            @Override
            public void onQueryAvailableValueInfoSuccess(List<AreaCodeBean> areaCodeBeanList) {
                if(isViewAttached()){
                    getView().onQueryAvailableValueInfoSuccess(areaCodeBeanList);
                }
            }

            @Override
            public void onQueryAvailableValueInfoError(String errMsg) {
                if(isViewAttached()){
                    getView().onQueryAvailableValueInfoError(errMsg);
                }
            }
        });

    }
}
