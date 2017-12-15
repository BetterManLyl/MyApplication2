package com.example.lyl.myapplication.Gesture;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.lyl.myapplication.R;
import com.takwolf.android.lock9.Lock9View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LStyleActivity extends AppCompatActivity {

    @BindView(R.id.lock_9_view)
    Lock9View lock9View;

    private int index = 0;
    private String unmCount = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_style);
        ButterKnife.bind(this);

        lock9View.setGestureCallback(new Lock9View.GestureCallback() {

            @Override
            public void onNodeConnected(@NonNull int[] numbers) {
                ToastUtils.with(LStyleActivity.this).show("+ " + numbers[numbers.length - 1]);
            }

            @Override
            public void onGestureFinished(@NonNull int[] numbers) {
                StringBuilder builder = new StringBuilder();
                for (int number : numbers) {
                    builder.append(number);
                }
                isRight(builder.toString());
                index++;

            }
        });
    }


    private void isRight(String builder) {
        if (isunmCountEmpty(unmCount)) {
            if (index > 0) {
                if (isLength(builder)) {
                    if (builder.toString().equals(unmCount)) {
                        ToastUtils.with(LStyleActivity.this).show("解锁成功");
                        finish();
                    } else {
                        ToastUtils.with(LStyleActivity.this).show("请重新输入手势");
                    }
                }
            }
        } else {
            if (isLength(builder)) {
                unmCount = builder.toString();
                ToastUtils.with(LStyleActivity.this).show("= " + builder.toString());
            }
        }
    }

    /**
     * 判断第一次赋值给unmCount是否为空
     *
     * @param num
     * @return
     */
    private boolean isunmCountEmpty(String num) {
        if (TextUtils.isEmpty(num)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断第一次的手势长度是否大于3
     *
     * @param builderL
     * @return
     */
    private boolean isLength(String builderL) {
        if (builderL.length() > 3) {
            return true;
        } else {
            if (!TextUtils.isEmpty(unmCount)) {
                return true;
            } else {
                ToastUtils.with(LStyleActivity.this).show("手势长度不够，三个以上");
                return false;
            }
        }
    }
}
