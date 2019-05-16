package com.example.along.mvpdemo.contract.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import com.example.along.mvpdemo.base.BasePresenter;
import com.example.along.mvpdemo.base.IBaseView;

import java.util.HashMap;

/**
 * 说明:  <br/>
 * 邮箱: yangbin5052@gmail.com <br/>
 * Create at 2018/3/14--11:17 by Luuren
 */
public interface LoginContract {

  interface View extends IBaseView {

    void onLoginTudcSuccess(String st, String uname, String avatar);

    void onLoginTudcError(int code, String errMsg);

    void onSmsCodeSuccess(String cc);

    void onSmsCodeError(int code, String errMsg);

    void onGetCountryCode(HashMap<String, String> countryHashMap);

    void onGetCcByCountry(String countryCode);

    void onTudcCheckPhoneNumberIsRegistedError(int code, String errMsg);

    void onTudcCheckPhoneNumberIsRegistedCompleled(boolean isRegisted, String cc);

    void facebookLoginTudcSuccess(String st, String uname, String avtar);

    void facebookLoginFail(int code, String errMsg);

    void facebookAuthSuccess(String openid, String token);

    void onLocationSuccess(double longitude, double latitude);

    void onLocationError(int code, String errMsg);

    void onLoginPalmcreditSuccess();
    void onLoginPalmCreditError(int code, String errMsg);
  }

  abstract class Presenter extends BasePresenter<View> {
    public abstract void checkPhoneNumberIsRegisted(String phoneNumber, String cc);

    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);

    public abstract void loginByPhoneSms(String cc, String phoneNumber, String verifyCode);

    public abstract void getSmsCode(String phoneNumber, String cc);

    public abstract void checkSmsCode();

    public abstract void getCountryCode();

    public abstract void getCountryCodeByCountry(String country);

    public abstract void loginByFacebook(Activity activity);

    public abstract void getLocationInfo();

    public abstract void loginPalmcredit(Context context, String countryCode, String phone, String nickName, String st, boolean userType, String longitude, String latitude, String country);

  }
}
