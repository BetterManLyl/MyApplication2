package com.example.lyl.myapplication.retrofit;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * @author lyl
 * @date 2017/11/24.
 * 定义上传接口
 */

public interface IPostFile {
    /**
     * 注意1：必须使用{@code @POST}注解为post请求<br>
     * 注意：使用{@code @Multipart}注解方法，必须使用{@code @Part}/<br>
     * {@code @PartMap}注解其参数<br>
     * 本接口中将文本数据和文件数据分为了两个参数，是为了方便将封装<br>
     * 也可以合并成一个{@code @Part}参数
     *
     * @return BaseResp为服务器返回的基本Json数据的Model类
     */

    @Multipart
    @POST("web/module/immediatelyHanlder.do")
    Observable<BaseResult> postFile(@PartMap Map<String, RequestBody> params,
                                    @Part List<MultipartBody.Part> parts);


    @Multipart
    @POST("web/module/immediatelyHanlder.do")
    Observable<ResponseBody> upload(@PartMap Map<String, RequestBody> params,
                                    @Header("token") String token,
                                    @Header("userName")String name
                                  );

}
