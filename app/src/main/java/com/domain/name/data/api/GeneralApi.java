package com.domain.name.data.api;

import com.domain.name.data.bean.ResultBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Liux on 2017/11/11.
 */

public interface GeneralApi {

    @GET("/category")
    Observable<ResultBean> industry(
            @Query("e_s[module]") String module
    );

    @FormUrlEncoded
    @POST("/user/authcompany")
    Observable<ResultBean> submitCompanyInfo(
            @FieldMap Map<String, String> stringStringMap
    );

    @FormUrlEncoded
    @PUT("/user/account/me")
    Observable<ResultBean> submitPresenInfo(
            @Field("is_user") int ok,
            @Field("category_id") int industry
    );

    /**
     * 单文件上传
     * @param part
     * @return
     */
    @Multipart
    @POST("/file")
    Observable<ResultBean> uploadFile(
            @Part MultipartBody.Part part
    );

    /**
     * 多文件上传
     * @param parts
     * @return
     */
    @Multipart
    @POST("/file")
    Observable<ResultBean> uploadFiles(
            @Part List<MultipartBody.Part> parts
    );
}
