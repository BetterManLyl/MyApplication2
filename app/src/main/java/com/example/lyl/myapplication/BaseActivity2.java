package com.example.lyl.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by lyl on 2017/9/30.
 */

public class BaseActivity2 extends AppCompatActivity {

    private Toast toast;

    public void showToast(String msg) {

        if (toast == null) {
            toast = Toast.makeText(this, msg + "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg+"");
        toast.show();
    }
}
