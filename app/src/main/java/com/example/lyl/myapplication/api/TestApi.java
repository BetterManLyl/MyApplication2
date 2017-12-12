package com.example.lyl.myapplication.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author lyl
 * @date 2017/11/19.
 * 登录测试
 */

public interface TestApi {
    @GET("/api/phone/logins")
    Observable<LoginSuccess> getLogin(@Query("loginName") String userName, @Query("password") String passWord);
}
