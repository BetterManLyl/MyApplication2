package com.example.lyl.myapplication.retrofit;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.lyl.myapplication.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author lyl
 * @date 2017/11/24.
 */

public class FilePostActivity extends AppCompatActivity {

    private Map<String, RequestBody> localHasmap = new HashMap<>();
    private String[] strings = {};
    private Button btn_post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postfileactivity);
        btn_post = (Button) findViewById(R.id.btn_post);
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postFile();
            }
        });

    }

    private List<File> fileList = new ArrayList<>();

    public void postFile() {
        File file = new File(Environment.getExternalStorageDirectory() + "/1.jpg");
        File file2 = new File(Environment.getExternalStorageDirectory() + "/2.jpg");
        fileList.add(file);
        fileList.add(file2);
        // 创建 RequestBody，用于封装构建RequestBody
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse("multipart/form-data"), file);

/**
 * 上传一张照片
 */
// MultipartBody.Part  和后端约定好Key，这里的partName是用image
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("photo0", "photo0.jpg", requestFile);


        Map<String, RequestBody> map = new HashMap<>();

// 添加描述
        String descriptionString = "hello, 这是文件描述";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);
        RequestBody detailId =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), "320");
        RequestBody hanldeTpye =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), "处理");
        /**
         * 上传多张照片
         */
        for (int i = 0; i < fileList.size(); i++) {
            /**
             * 注意拼接的方式
             * name=”file0”; filename=”test.png”这个请求头是根据有心课堂提供的上传接口写的，
             * 不适用其他上传接口，但原理是类似的；
             *单张图片上传通用的请求头是：name=”file”; filename=”test.png”
             *filename=”test.png”这个一般是指（你希望）保存在服务器的文件名字。
             */
            map.put("photo" + i + "\"; filename=\"" + "photo" + fileList.get(i).getName(),
                    RequestBody.create(MediaType.parse("image/png"), fileList.get(i)));
        }

        map.put("describe", description);
        map.put("detailId", detailId);
        map.put("hanldeTpye", hanldeTpye);

        ApiService.postFile()
                .upload(map, "FB3609316B11484F30B1B4DCACF5A065",
                        "zlq")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        Log.e("lyl", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("lyl", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.e("lyl", "onNext: ");
                    }
                });


        // BasePostFile.files2Parts()
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                if (hideInputMethod(this, v)) {
                    return true; //隐藏键盘时，其他控件不响应点击事件==》注释则不拦截点击事件
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null) {
            if (v instanceof EditText) {
                int[] leftTop = {0, 0};
                v.getLocationInWindow(leftTop);
                int left = leftTop[0], top = leftTop[1], bottom = top + v.getHeight(), right = left
                        + v.getWidth();
                if (event.getX() > left && event.getX() < right
                        && event.getY() > top && event.getY() < bottom) {
                    // 保留点击EditText的事件
                    Log.e("lyl", "isShouldHideInput: ");
                    // v.setFocusable(false);
                    return false;
                } else {
                    // v.setFocusable(false);
                    Log.e("lyl", "HideInput: ");
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean hideInputMethod(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return false;
    }
}
