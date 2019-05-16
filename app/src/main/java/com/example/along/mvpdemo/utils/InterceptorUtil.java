package com.example.along.mvpdemo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import com.example.along.mvpdemo.constant.Constants;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;

/**
 * @author long
 * @date 2018/12/3
 * @description 拦截器工具类!
 */
public class InterceptorUtil {
    private static final String TAG = "http";
    private static String Token = "";
    public final static Charset UTF8 = Charset.forName("UTF-8");

//    //日志拦截器
//    public static HttpLoggingInterceptor LogInterceptor() {
//        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                LogUtil.e("token", message);
//            }
//        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
//    }

    /**
     * token验证的拦截器
     *
     * @return
     */
    public static Interceptor tokenInterceptor(final Context context, final boolean isShowLog) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                String token = PrefUtils.getString(context, Constants.ACCESS_TOKEN, "");
                if (!token.equals("")) {
                    builder.addHeader(Constants.TOKEN_NAME, token);
                    LogUtil.e(TAG, "intercept: token   " + token);
                }

                Response mResponse = chain.proceed(builder.build());
                String new_header = mResponse.header(Constants.TOKEN_NAME);
                Headers requestHeaders = mResponse.headers();
                int requestHeadersLength = requestHeaders.size();
                String netMessage = "返回信息---------------------->" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date()) + "--------请求url----------->" + builder.build().url();
                for (int i = 0; i < requestHeadersLength; i++) {
                    String headerName = requestHeaders.name(i);
                    String headerValue = requestHeaders.get(headerName);
//                Log.v("token", "请求头----------->Name:" + headerName + "------------>Value:" + headerValue);
                    netMessage = netMessage + "请求头----------->Name:" + headerName + "------------>Value:" + headerValue + "\n";
                }
                if (!TextUtils.isEmpty(new_header)) {
                    PrefUtils.setString(context, Constants.ACCESS_TOKEN, new_header);
                }

                if (isShowLog) {
                    Request request = chain.request();
                    LogUtil.e(TAG, "请求地址：| " + request.toString());
                    printParams(request.body());

                    ResponseBody body = mResponse.body();
                    if (body != null) {
                        MediaType mediaType = body.contentType();
                        if (mediaType != null) {
                            LogUtil.e(TAG, "responseBody's contentType : " + mediaType.toString());
                            if (isText(mediaType)) {
                                String resp = body.string();
                                LogUtil.e(TAG, "responseBody's content : " + resp);
                                body = ResponseBody.create(mediaType, resp);
                                return mResponse.newBuilder().body(body).build();
                            } else {
                                LogUtil.e(TAG, "responseBody's content : " + " maybe [file part] , too large too print , ignored!");
                            }
                        }
                    }
                }

                return mResponse;
            }
        };
    }

    public static boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    ) {
                return true;
            }
        }
        return false;
    }

    private static void printParams(RequestBody body) {
        Buffer buffer = new Buffer();
        try {
            body.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            String params = buffer.readString(charset);
            LogUtil.e(TAG, "请求参数： | " + params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
