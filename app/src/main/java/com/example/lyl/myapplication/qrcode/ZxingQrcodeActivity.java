package com.example.lyl.myapplication.qrcode;

import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.base.BaseActivity;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * @author lyl
 * @date 2017/12/15.
 * 二维码扫描
 * // github地址   https://github.com/bingoogolapple/BGAQRCode-Android
 */

public class ZxingQrcodeActivity extends BaseActivity implements QRCodeView.Delegate {
    @BindView(R.id.zxingview)
    ZXingView mQRCodeView;

    @Override
    public int getLayoutId() {
        return R.layout.qrcode;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initView() {
        super.initView();
        mQRCodeView.setDelegate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
        mQRCodeView.showScanRect();
        mQRCodeView.startSpot();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        //  Log.i(TAG, "result:" + result);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e("lyl1", "打开相机出错");
    }
}
