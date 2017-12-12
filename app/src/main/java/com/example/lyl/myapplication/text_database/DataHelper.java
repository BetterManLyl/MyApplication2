package com.example.lyl.myapplication.text_database;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by lyl on 2017/8/16.
 * <p>
 * 这个类就是实现从assets目录读取数据库文件然后写入SDcard中,如果在SDcard中存在，就打开数据库，不存在就从assets目录下复制过去
 */

public class DataHelper {

    //数据库存储的路径
    private String filePath = "data/data/com.example.lyl.myapplication/test.db";
    //数据库存放的文件夹 data/data/com.main.jh 下面
    String pathStr = "data/data/com.example.lyl.myapplication";
    SQLiteDatabase database;

    public SQLiteDatabase openDataBase(Context context) {


        File jhPath = new File(filePath);
        //查看数据库文件是否存在
        if (jhPath.exists()) {
            Log.i("test", "存在数据库");
            //存在则直接返回打开的数据库
            return SQLiteDatabase.openOrCreateDatabase(jhPath, null);
        } else {
            //不存在先创建文件夹
            File path = new File(pathStr);
            Log.i("test", "pathStr=" + path);
            if (path.mkdir()) {
                Log.i("test", "创建成功");
            } else {
                Log.i("test", "创建失败");
            }
            ;
            //得到资源
            AssetManager assetManager = context.getAssets();
            try {
                //得到数据库的输入流
                InputStream inputStream = assetManager.open("test.db");
                Log.i("test", inputStream + "");
                //用输出流写到SDcard上面
                FileOutputStream fileOutputStream = new FileOutputStream(jhPath);
                Log.i("test", "fos=" + fileOutputStream);
                Log.i("test", "jhPath=" + jhPath);
                //创建byte数组  用于1KB写一次
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = inputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, count);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }
        return openDataBase(context);
    }
}
