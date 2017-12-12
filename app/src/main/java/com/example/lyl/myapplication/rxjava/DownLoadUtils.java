package com.example.lyl.myapplication.rxjava;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * @author lyl
 * @date 2017/11/16.
 */

public class DownLoadUtils {

    private OkHttpClient okHttpClient;

    public DownLoadUtils() {

        okHttpClient = new OkHttpClient();
    }

    public Observable<byte[]> getBytes(String path) {
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    Request request = new Request.Builder().url(path).build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                byte[] bytes = response.body().bytes();
                                if (bytes != null) {
                                    subscriber.onNext(bytes);
                                }
                            }
                            subscriber.onCompleted();
                        }
                    });
                }
            }
        });
    }
}
