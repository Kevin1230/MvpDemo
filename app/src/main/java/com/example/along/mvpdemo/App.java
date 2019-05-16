package com.example.along.mvpdemo;

import android.app.Application;
import android.view.Gravity;

import com.blankj.utilcode.util.SPUtils;
import com.example.along.mvpdemo.api.Api;
import com.example.along.mvpdemo.constant.Constants;
import com.example.along.mvpdemo.utils.LoggerInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 说明:  入口 <br/>
 */
public class App extends Application {

    public static Api api;

    private long time = -2;
    private int count = 0;

    /**
     * 记录锁屏时间
     */
    public static long RECORDING_TIME = 0;
    /**
     * 判断是否进入其他应用
     */
    public static boolean isToOtherApp = false;

    @Override
    public void onCreate() {
        super.onCreate();

        initUtils();
        //   initRetrofit();


    }

    private void initUtils() {

    }

  /*  private void initRetrofit() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    //添加共有Header
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader(Constants.TOKEN_NAME, "");
                        return chain.proceed(builder.build());
                    }
                })
                .addInterceptor(
                        new LoggerInterceptor("http", true)
                )
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .readTimeout(15000, TimeUnit.MILLISECONDS)
                .build();

        api = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(Api.class);
    }*/
}
