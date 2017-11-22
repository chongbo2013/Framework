package com.domain.name.data.api;

import com.domain.name.data.bean.ResultBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Liux on 2017/11/11.
 */

public interface GeneralApi {

    /**
     * GET 请求
     * @param phone
     * @return
     */
    @GET("/auth/send")
    Observable<ResultBean> get(
            @Query("username") String phone
    );

    /**
     * POST 请求
     * @param phone
     * @param password
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("/auth/login")
    Observable<ResultBean> login(
            @Field("username") String phone,
            @Field("password") String password,
            @Field("login_type") int type
    );

    /**
     * 单文件上传
     * @param part
     * @return
     */
    @Multipart
    @POST("/api/upload")
    Observable<ResultBean> uploadFile(
            @Part MultipartBody.Part part
    );

    /**
     * 多文件上传
     * @param parts
     * @return
     */
    @Multipart
    @POST("/api/uploads")
    Observable<ResultBean> uploadFiles(
            @Part List<MultipartBody.Part> parts
    );
}
