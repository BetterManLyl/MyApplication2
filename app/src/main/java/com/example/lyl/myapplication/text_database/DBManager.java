package com.example.lyl.myapplication.text_database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lyl on 2017/8/16.
 */

public class DBManager {
    private String DB_NAME = "weather_city.db";
    private Context mContext;

    public DBManager(Context mContext) {
        this.mContext = mContext;
    }
    //把assets目录下的db文件复制到dbpath下
    public SQLiteDatabase DBManager(String packName) {
        String dbPath = "/data/data/" + packName
                + "/databases/" + DB_NAME;
        if (!new File(dbPath).exists()) {
            try {
                FileOutputStream out = new FileOutputStream(dbPath);
                InputStream in = mContext.getAssets().open("weather_city.db");
                byte[] buffer = new byte[1024];
                int readBytes = 0;
                while ((readBytes = in.read(buffer)) != -1)
                    out.write(buffer, 0, readBytes);
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openOrCreateDatabase(dbPath, null);
    }
    //查询
    public Citys query(SQLiteDatabase sqliteDB, String[] columns, String selection, String[] selectionArgs) {
        Citys city = null;
        try {
            String table = "Citys";
            Cursor cursor = sqliteDB.query(table, columns, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                String parentCity = cursor.getString(cursor
                        .getColumnIndex("parent"));
                String phoneCode = cursor.getString(cursor.getColumnIndex("phone_code"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
                String cityID = cursor.getString(cursor.getColumnIndex("posID"));
                String areaCode = cursor.getString(cursor.getColumnIndex("area_code"));
                city = new Citys(parentCity, name, pinyin, phoneCode, cityID, areaCode);
                cursor.moveToNext();
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }
}
