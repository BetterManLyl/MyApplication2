package com.example.lyl.myapplication.testpermissionrequest;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.lyl.myapplication.BaseActivity;
import com.example.lyl.myapplication.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * @author lyl
 * @date 2017/11/10.
 * 1、在清单文件中申请需要的权限
 * 2、检查权限
 * 3、手动申请权限
 */

public class PermissionRequestActivity extends BaseActivity {

    private static final int ACCESS_COARSE_LOCATION = 1;
    private static final int ACCESS_FINE_LOCATION = 2;
    private Button btn_location;


    RxPermissions rxPermissions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_permission);
        rxPermissions = new RxPermissions(this);
        initView();
        //检查权限
        // checkPer();

    }

    private void initView() {
        btn_location = (Button) findViewById(R.id.btn_location);
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPer();
            }
        });

    }

    private void checkPer() {
        //方法返回值为PackageManager.PERMISSION_DENIED或者PackageManager.PERMISSION_GRANTED。当返回DENIED就需要进行申请授权了。
        //PackageManager.PERMISSION_GRANTED;
        //2、检测权限
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
//            //3、申请权限
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, ACCESS_COARSE_LOCATION);
//
//        }else{
//            showToast("已经授予该权限");
//        }



//        rxPermissions.request(Manifest.permission.CAMERA,
//                Manifest.permission.READ_PHONE_STATE).subscribe(new Observer<Boolean>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Boolean value) {
//
//                if (value) {
//                    showToast("同意");
//                } else {
//                    showToast("拒绝");
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showToast("允许定位");
                } else {
                    showToast("提示信息");
                    showToast("拒绝定位");
                }
                break;
            case ACCESS_FINE_LOCATION:
                break;
            default:
                break;
        }
    }
}
