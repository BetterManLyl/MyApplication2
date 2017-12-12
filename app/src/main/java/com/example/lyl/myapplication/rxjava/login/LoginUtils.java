package com.example.lyl.myapplication.rxjava.login;

import com.example.lyl.myapplication.rxjava.login.entity.Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * @author lyl
 * @date 2017/11/17.
 */

public class LoginUtils {

    private OkHttpClient okHttpClient;

    public LoginUtils() {
        if (okHttpClient==null){
            okHttpClient = new OkHttpClient();
        }
    }

    public Observable<JSONObject> getObserString(String url,Map<String, String> params) {
        return Observable.create(new Observable.OnSubscribe<JSONObject>() {
            @Override
            public void call(Subscriber<? super JSONObject> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    FormBody.Builder formBody = new FormBody.Builder();
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        formBody.add(entry.getKey(), entry.getValue());
                    }
                    RequestBody requestBody = formBody.build();
                    Request request = new Request.Builder()
                            .url(url)
                            .post(requestBody)
                            .build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response != null) {
                                try {
                                    subscriber.onNext(new JSONObject(response.body().string()));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}
