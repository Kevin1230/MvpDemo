package com.example.along.mvpdemo.api;


import com.example.along.mvpdemo.base.BaseEntity;
import com.example.along.mvpdemo.model.bean.AreaCodeBean;
import com.example.along.mvpdemo.model.bean.AreaCodeParams;
import com.example.along.mvpdemo.model.bean.LanguageBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 说明:  接口<br/>
 * Create at 2017/12/22--10:06 by long
 */
public interface Api {

    String BASE_URL = "http://102.85.85.185/";

    /**
     * H5本地
     */
    String H5_HOST = "https://fintech.mana.com/h5/";

    //  String H5_MAIN = "#/";
    //UAT
    String H5_MAIN = "";

    /**
     * 商汤解析数据URL
     */
    String LINKFACE_URL_IDCARD = "https://cloudapi.linkface.cn/ocr/parse_idcard_ocr_result";

    String LINKFACE_URL_BANKCRD = "https://cloudapi.linkface.cn/ocr/parse_bankcard_ocr_result";


//    /**
//     * 上传文件
//     */
//    @POST("approval/uploadFile.do")
//    Observable<BaseEntity<UploadBean>> uploadFile(@Body MultipartBody multipartBody);
//
//    /**
//     * 商汤解析数据
//     */
//    @POST
//    Observable<ResponseBody> parseLinkfaceData(@Url String url, @Body MultipartBody multipartBody);


    /** 获取国别码 */
    @POST("api/auth/getarea")
    Observable<BaseEntity<List<AreaCodeBean>>> getArea(@Body AreaCodeParams areaCodeParams);

    /**选择语言列表*/
    @POST("/api/getlanguage")
    Observable<BaseEntity<List<LanguageBean>>> getlanguage(@Body Map<String,String> map);

}
