package com.example.lyl.myapplication.testmina;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.example.lyl.myapplication.R;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by lyl on 2017/7/13.
 */

public class ClientMinaActivity extends AppCompatActivity {
    private Button btn_conn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_conn= (Button) findViewById(R.id.connect);


        btn_conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建客户端连接器
                NioSocketConnector connector = new NioSocketConnector();
                //设置事件处理器
                //connector.setHandler(new MyHandler());
                //设置编码过滤器
                connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
                //建立连接
                ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", 9898));
                //等待连接创建完成
                future.awaitUninterruptibly();
                // 获取当前session
                IoSession session = future.getSession();
                session.write("");
//                BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
//                String inputContent;
//                //发送消息到服务器端
//                while (!(inputContent = inputReader.readLine()).equals("bye")) {
//
//                }
            }
        });
    }
}
