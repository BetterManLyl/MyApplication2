package com.example.lyl.myapplication.layoutinflate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lyl
 * @date 2017/12/5.
 * <p>
 * <p>
 * 布局添加方法的区别
 * LayoutInflate
 * <p>
 * <p>
 * 参考博客
 * http://blog.csdn.net/u012702547/article/details/52628453
 */

public class InflateActivity extends BaseActivity {

    @BindView(R.id.ll_lin)
    LinearLayout llLin;


    @Override
    public int getLayoutId() {
        return R.layout.layout_inflate;
    }

    @Override
    public void getExtraValue() {
        super.getExtraValue();
        showToast("getExtraValue");
        LogUtils.eTag("lyl1", ActivityUtils.getActivityList());

    }

    /**
     * 添加布局 两个参数
     */
    private void layoutInTwofalse() {
        View view = LayoutInflater.from(this).inflate(R.layout.inflate_view, null);
        llLin.addView(view);
    }

    /**
     * 添加布局 两个参数
     */
    private void layoutInTwotrue() {
        View view = LayoutInflater.from(this).inflate(R.layout.inflate_view, llLin);
        //llLin.addView(view);
    }

    /**
     * 添加布局 三个参数 view true
     * <p>
     * 1、当root不为null，attachToRoot为true时，表示将resource指定的布局添加到root中，
     * 添加的过程中resource所指定的的布局的根节点的各个属性都是有效的
     * <p>
     * <p>
     * 2、这里我都没写将inflate出来的View添加到ll中的代码，但是linearlayout布局文件就已经添加进来了，
     * 这就是因为我第三个参数设置为了true，表示将第一个参数所指定的布局添加到第二个参数的View中
     */
    private void layoutIntrue() {
        View view = LayoutInflater.from(this).inflate(R.layout.inflate_view, llLin, true);
        //如果第三个参数为true，则不能在调用addView()方法
        //原因就是因为当第三个参数为true时，会自动将第一个参数所指定的View添加到第二个参数所指定的View中。
        //llLin.addView(view);
    }

    /**
     * 添加布局 三个参数 view false
     */
    private void layoutInfalse() {
        View view = LayoutInflater.from(this).inflate(R.layout.inflate_view, llLin, false);
        llLin.addView(view);
    }

    /**
     * 添加布局 三个参数 null true
     */
    private void layoutIntruenull() {
        View view = LayoutInflater.from(this).inflate(R.layout.inflate_view, null, true);
        llLin.addView(view);
    }

    /**
     * 添加布局 三个参数 null false
     */
    private void layoutInfalsenull() {
        View view = LayoutInflater.from(this).inflate(R.layout.inflate_view, null, false);
        llLin.addView(view);
    }


    /**
     * 清除所有的布局
     */
    public void cleanView() {
        llLin.removeAllViews();
    }

    @OnClick({R.id.btn_false, R.id.btn_true, R.id.btn_two_false,
            R.id.btn_two_true, R.id.btn_true_null, R.id.btn_false_null, R.id.btn_clean,
            R.id.btn_gohome, R.id.btn_isdebug})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_false:
                showToast("message");
                layoutInfalse();
                break;
            case R.id.btn_true:
                layoutIntrue();
                break;
            case R.id.btn_two_false:
                layoutInTwofalse();
                break;
            case R.id.btn_two_true:
                layoutInTwotrue();
                break;
            case R.id.btn_true_null:
                layoutIntruenull();
                break;
            case R.id.btn_false_null:
                layoutInfalsenull();
                break;
            case R.id.btn_clean:
                cleanView();
                break;
            case R.id.btn_gohome:
                //回到桌面
                ActivityUtils.startHomeActivity();
                break;
            case R.id.btn_isdebug:
                //判断app是否是debug版本
                AppUtils.isAppDebug();
                ToastUtils.showShort(AppUtils.isAppDebug() + "");
                break;
            default:
                break;
        }
    }

}
