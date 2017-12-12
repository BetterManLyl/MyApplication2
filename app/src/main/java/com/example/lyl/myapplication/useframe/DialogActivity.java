package com.example.lyl.myapplication.useframe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.example.lyl.myapplication.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author lyl
 * @date 2017/12/1.
 * 弹出框  http://blog.csdn.net/u010904027/article/details/53535590
 */

public class DialogActivity extends AppCompatActivity {


    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        unbinder = ButterKnife.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    //简单的dialog
    @OnClick(R.id.btn_dialog_one)
    public void onViewClicked() {
        new MaterialDialog.Builder(DialogActivity.this)
                .title("标题")
                .content("这里是主要内容")
                //按钮的背景色
                // .btnSelector(R.color.blue)
                .positiveText("确认")
                .negativeText("取消")
                .neutralText("更多信息")
                //弹框主题
                .theme(Theme.LIGHT)
                //多选列表
                .items(R.array.array)
                //不再提醒点击后的颜色
                .widgetColor(Color.LTGRAY)
                //有复选框
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        Toast.makeText(DialogActivity.this, "text:" + text, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                })
                //如果使用这种点击  则是单个点击效果  没有复选框
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        Toast.makeText(DialogActivity.this, "text:" + text, Toast.LENGTH_SHORT).show();
                    }
                })
                //不再提醒  默认选中   监听事件
                .checkBoxPrompt("不再提醒", true, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Toast.makeText(DialogActivity.this, "isCheck" + isChecked, Toast.LENGTH_SHORT).show();
                    }
                })
                // .theme(Theme.DARK)
                //监听按钮点击事件方法一
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.NEGATIVE) {
                            Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        } else if (which == DialogAction.POSITIVE) {
                            Toast.makeText(DialogActivity.this, "确认", Toast.LENGTH_SHORT).show();
                        } else if (which == DialogAction.NEUTRAL) {
                            Toast.makeText(DialogActivity.this, "更多信息", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                //按钮的背景颜色

                //监听按钮点击事件方法二
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                    }
//                })
                // .cancelable(false)//点击外部是否能取消
                .iconRes(R.mipmap.ic_launcher)
                .show();
    }
}
