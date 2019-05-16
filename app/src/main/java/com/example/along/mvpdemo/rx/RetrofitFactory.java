package com.example.along.mvpdemo.rx;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.example.along.mvpdemo.api.Api;
import com.example.along.mvpdemo.constant.Constants;
import com.example.along.mvpdemo.utils.InterceptorUtil;
import com.example.along.mvpdemo.utils.LogUtil;
import com.example.along.mvpdemo.utils.LoggerInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static RetrofitFactory mInstance;
    private static Api api;

    private RetrofitFactory(Context context) {
        super();
        //在构造方法中完成对Retrofit接口的初始化
        File cacheFile = new File(context.getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new Interceptor() {
//                    //添加共有Header
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request.Builder builder = chain.request().newBuilder();
//                        builder.addHeader(Constants.TOKEN_NAME, "");
//                        return chain.proceed(builder.build());
//                    }
//                })
                .addInterceptor(
                       // new LoggerInterceptor("http", true)
                        InterceptorUtil.tokenInterceptor(context,true)
                )
             //   .cache(cache)
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

    }

    public static RetrofitFactory getInstance(Context context) {
        if (mInstance == null) {
            synchronized (RetrofitFactory.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitFactory(context);

                }
            }
        }
        return mInstance;
    }
    public Api API() {
        return api;
    }
}
