package com.example.lyl.myapplication.okhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lyl.myapplication.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import retrofit2.Call;

/**
 * @author lyl
 * @date 2017/11/24.
 */

public class OkHttpActivity extends AppCompatActivity {
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.userpass)
    EditText userpass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_post)
    public void onCilck() {
        Toast.makeText(this, "btn_click", Toast.LENGTH_SHORT).show();
        post();
    }

    /**
     * 请求
     */
    private void post() {

        OkHttpClient ok = new OkHttpClient();
        FormBody.Builder f = new FormBody.Builder();
        f.add("userName", username.getText().toString());
        f.add("userpass", userpass.getText().toString());
        RequestBody re = f.build();

        Request requ = new Request.Builder()
                .url("http://36.7.144.130/web/entrance/phoneLogin.do")
                .post(re)
                .build();
        okhttp3.Call ca = ok.newCall(requ);
        ca.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("lyl", "onFailure: ");
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Log.e("lyl", "onResponse: " + response.body().toString());
            }
        });
    }
}
