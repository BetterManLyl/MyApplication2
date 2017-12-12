package com.example.lyl.myapplication.testsocket;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lyl.myapplication.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.security.auth.login.LoginException;

/**
 * Created by lyl on 2017/7/18.
 */

public class MainActivity extends AppCompatActivity implements Runnable {
    private TextView tv_msg = null;
    private EditText ed_msg = null;
    private Button btn_send = null;
    // private Button btn_login = null;
    private static final String HOST = "199.28.0.234";
    private static final int PORT = 9999;
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String content = "";
    //接收线程发送过来信息，并用TextView显示
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                tv_msg.setText(msg.obj.toString());
            }


        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testsocket);

        tv_msg = (TextView) findViewById(R.id.show);
        ed_msg = (EditText) findViewById(R.id.input);
        btn_send = (Button) findViewById(R.id.connect);

       // new Handler();
        //不能new 对象
       // Looper looper=new Looper();
//        Looper.loop();
//        Looper.prepare();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        socket = new Socket(HOST, PORT);
                        in = new BufferedReader(new InputStreamReader(socket
                                .getInputStream()));
                        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                                socket.getOutputStream())), true);
                        try {
                            boolean isCo=true;
                            while (isCo) {
                                if (!socket.isClosed()) {
                                    Log.e("lyl", "run: 1");
                                    if (socket.isConnected()) {
                                        Log.e("lyl", "run: 2");
                                        if (!socket.isInputShutdown()) {
                                            Log.e("lyl", "run: 3");
                                            if ((content = in.readLine()) != null) {
                                                Log.e("lyl", "run: 4");
                                                content += "\n";
                                                Message me=new Message();
                                                me.what=1;
                                                me.obj=content;
                                                mHandler.sendMessage(me);

                                            } else {
                                                isCo=false;
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();



        btn_send.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String msg = ed_msg.getText().toString();
                if (socket.isConnected()) {
                    if (!socket.isOutputShutdown()) {
                        out.println(msg);
                        tv_msg.setText("7777");
                    }
                }
            }
        });
        //启动线程，接收服务器发送过来的数据
        new Thread(MainActivity.this).start();
    }

    /**
     * 读取服务器发来的信息，并通过Handler发给UI线程
     */
    public void run() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (socket!=null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (in!=null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out!=null){
            out.close();
        }
    }
}
