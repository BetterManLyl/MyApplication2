package com.example.lyl.myapplication.text_database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lyl.myapplication.BaseActivity;
import com.example.lyl.myapplication.R;

/**
 * Created by lyl on 2017/8/16.
 * <p>
 * <p>查询assets文件夹下的数据库
 *
 */

public class DataBaseActivity extends BaseActivity {
    private Button btn_text;
    private TextView tv_show;
    private SQLiteDatabase db1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_database);
        btn_text = (Button) findViewById(R.id.btn_text);
        tv_show = (TextView) findViewById(R.id.tv_show);
// 初始化，只需要调用一次
        AssetsDatabaseManager.initManager(getApplication());
// 获取管理对象，因为数据库需要通过管理对象才能够获取
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
// 通过管理对象获取数据库
        db1 = mg.getDatabase("test.db");
// 对数据库进行操作
        //db1.execSQL("insert into tb([ID],[content]) values(null, 'db1');");
        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData() {
//        DataHelper data = new DataHelper();
//        SQLiteDatabase s = data.openDataBase(this);
        //查询数据库中testid=1的数据
        Cursor cursor = db1.rawQuery("select * from test where age=?", new String[]{"24"});
        String name = null;
        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex("name"));
        }
        //这是一个TextView，把得到的数据库中的name显示出来.
        tv_show.setText(name);
        cursor.close();
    }
}
