package com.example.along.mvpdemo.model;

import android.content.Context;

import com.example.along.mvpdemo.base.BaseEntity;
import com.example.along.mvpdemo.model.bean.AreaCodeBean;
import com.example.along.mvpdemo.model.bean.AreaCodeParams;
import com.example.along.mvpdemo.rx.BaseObserver;
import com.example.along.mvpdemo.rx.RetrofitFactory;
import com.example.along.mvpdemo.rx.RxHelper;

import java.util.List;


/**
 * Created by long on 18-4-23.
 */

public class HomeModel implements IHomeModel {

    @Override
    public void queryAvailableValunInfo(final Context context, String keyword, String lang, final OnQueryCustLmtListener onQueryCustLmtListener) {
        AreaCodeParams areaCodeParams = new AreaCodeParams("", "cn");
        RetrofitFactory.getInstance(context).API().getArea(areaCodeParams)
                .compose(RxHelper.<BaseEntity<List<AreaCodeBean>>>io_main())
                .subscribe(new BaseObserver<List<AreaCodeBean>>(context) {
                    @Override
                    public void onSuccess(List<AreaCodeBean> areaCodeBeanList, String msg) {
                        if (onQueryCustLmtListener != null) {
                            onQueryCustLmtListener.onQueryAvailableValueInfoSuccess(areaCodeBeanList);
                        }
                    }

                    @Override
                    protected void onFailure(String msg) {
                        if (onQueryCustLmtListener != null) {
                            onQueryCustLmtListener.onQueryAvailableValueInfoError(msg);
                        }
                    }
                });

    }

}
