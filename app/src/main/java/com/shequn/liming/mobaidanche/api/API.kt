package com.shequn.liming.mobaidanche.api

import com.shequn.liming.mobaidanche.api.bean.User
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Created by Liming on 2017/7/25.
 */

interface ApiService {

    /**
     * 获取广告图片
     */
    @GET("test/getAdvertisementImage")
    fun getAdvertisementImage(): Observable<HttpResult<String?>>

    /**
     * 获取验证码
     */
    @GET("test/getVerificationCode")
    fun getVerificationCode(@Query("phoneNum")phoneNum: String): Observable<HttpResult<String?>>

    /**
     * 登录
     */
    @GET("test/loginOrRegister")
    fun loginOrRegister(@Query("phoneNum")phoneNum: String, @Query("verificationCode")verificationCode: String): Observable<HttpResult<User?>>

    /**
     * 解锁车辆
     */
    @GET("test/openLock")
    fun openLock(@Query("bikeNumber")bikeNumber: String, @Query("userId")userId: String ): Observable<HttpResult<String?>>
}

object API {

    val apiService: ApiService by lazy<ApiService> {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(2, TimeUnit.SECONDS)
                .build()
        Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://192.168.1.107:8080/")
                .build()
                .create(ApiService::class.java)
    }
}