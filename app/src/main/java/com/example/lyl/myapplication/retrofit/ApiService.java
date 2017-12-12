package com.example.lyl.myapplication.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author lyl
 * @date 2017/11/24.
 */

public class ApiService {

    private static String baseUrl = "http://36.7.144.130/";
    private static OkHttpClient okHttpClient = null;
    private static Retrofit retrofit;
    private static Converter.Factory converter = GsonConverterFactory.create();
    private static CallAdapter.Factory factory = RxJavaCallAdapterFactory.create();

    private static int CONNENT_TIME_OUT = 5000;
    private static int WRITE_TIME_OUT = 5000;
    private static int READ_TIME_OUT = 5000;

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(factory)
                    .addConverterFactory(converter)
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }


    /**
     * 获取OkHttpClient
     * 设置连接网络超时时间
     * 设置读写的超时时间
     *
     * @return
     */
    private static OkHttpClient getOkHttpClient() {

        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(CONNENT_TIME_OUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
                    .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                    .build();
        }
        return okHttpClient;
    }


    /**
     * 上传文件
     * 上传多个文件
     *
     * @return
     */
    public static IPostFile postFile() {
        return getRetrofit().create(IPostFile.class);
    }

}
