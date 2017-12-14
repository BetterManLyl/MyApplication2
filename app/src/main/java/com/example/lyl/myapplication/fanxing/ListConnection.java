package com.example.lyl.myapplication.fanxing;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * @author lyl
 * @date 2017/12/14.
 */

public class ListConnection<T> extends AppCompatActivity{
    public void say(T t) {
        Log.e("lyl1", "say: "+t.toString() );
       // Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
    }

    public static void main(String[] args) {
        ListConnection<String> listConnection = new ListConnection<>();
        listConnection.say("12");
    }
}
