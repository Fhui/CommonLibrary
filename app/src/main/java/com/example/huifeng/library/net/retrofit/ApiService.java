package com.example.huifeng.library.net.retrofit;

import com.example.huifeng.library.bean.AllContentBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Retrofit API
 * Created by ShineF on 2017/7/6 0006.
 */


public interface ApiService {

    /**
     * Get请求, 去替换{}包裹的字段, @Path替换注释
     * 使用API Gank.IO
     * @param item 条数
     * @param page 页数
     * @return 请求参数
     */
    @GET("history/content/{item}/{page}")
    Call<String> loadHistory(@Path("item") int item, @Path("page") int page);

    @GET("data/all/{item}/{page}")
    Observable<AllContentBean> loadAllData(@Path("item") int item, @Path("page") int page);

    @FormUrlEncoded
    @POST("idl_baidu/clothing_classification/clothing_classification")
    Call<String> loadBaiDuAttribute(@FieldMap Map<String, Object> map);


}
