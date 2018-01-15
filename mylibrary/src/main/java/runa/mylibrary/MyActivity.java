package runa.mylibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.AndroidCharacter;

/**
 * @author lyl
 * @date 2017/12/15.
 * 测试依赖外部jar 创建jar包给别人使用
 */

public class MyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity);


    }
}
