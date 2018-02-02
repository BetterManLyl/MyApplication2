package com.example.lyl.myapplication.rxjava;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * @author lyl
 * @date 2018/1/15.
 */

public class MyCallBack<T> implements Callback<T> {

    private ProgressDialog progressDialog;

    private Context context;


    public MyCallBack() {


    }

    public <T> MyCallBack(Context context) {
        this.context = context;
    }

    private void initDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.setMessage("登录中");
        progressDialog.show();
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        initDialog();
    }

    @Override
    public void onSuccess(Response<T> response) {
        dialogDiss();
    }

    public void dialogDiss() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onCacheSuccess(Response<T> response) {

    }

    @Override
    public void onError(Response<T> response) {
        dialogDiss();
    }

    @Override
    public void onFinish() {
        dialogDiss();
    }

    @Override
    public void uploadProgress(Progress progress) {

    }

    @Override
    public void downloadProgress(Progress progress) {

    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        return null;
    }
}
