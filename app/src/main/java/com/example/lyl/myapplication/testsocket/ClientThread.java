package com.example.lyl.myapplication.testsocket;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by lyl on 2017/7/18.
 */

public class ClientThread implements Runnable {
    private String input;
    private Socket socket;
    private BufferedReader br;
    private OutputStream os;
    // 定义向UI线程发送消息的Handler对象
    Handler handler;
    // 定义接收UI线程的Handler对象
    Handler revHandler;

    public ClientThread(Handler handler,Socket socket) {
        this.handler = handler;
        this.socket=socket;
    }

    public ClientThread(String input) {
        this.input = input;
    }

    @Override
    public void run() {
        try {
            if (socket.isConnected()) {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                os=socket.getOutputStream();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String content = "";
                        //不断的读取服务端发送过来的消息
                        try {
                            while ((content = br.readLine()) != null) {
                                Message message = new Message();
                                message.what = 0x123;
                                message.obj = content;
                                handler.sendMessage(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
            // 为当前线程初始化Looper
            Looper.prepare();
            // 创建revHandler对象
            revHandler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what==0x345){
                        // 将用户在文本框输入的内容写入网络
                        try {
                            os.write((msg.obj.toString() + "\r\n")
                                    .getBytes("UTF-8"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            // 启动Looper
            Looper.loop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
