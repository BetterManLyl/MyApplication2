package com.example.lyl.myapplication.testdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.lyl.myapplication.BaseActivity2;
import com.example.lyl.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyl on 2017/7/23.
 */

public class DialogActivity extends BaseActivity2 {

    private Button btn_dialog;
    AlertDialog.Builder al;
    private GridAdapter gridAdapter;
    private List<Item> list = new ArrayList<>();
    private GridView gridView;
    private Button bnt_selected, btn_selected_all, btn_selected_all_delete;
    private StringBuffer sb;

    private Button toast;
    private int i=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_view);
        initData();
        btn_dialog = (Button) findViewById(R.id.btn_dialog);
        gridView = (GridView) findViewById(R.id.grid_view);
        bnt_selected = (Button) findViewById(R.id.btn_selected);
        btn_selected_all = (Button) findViewById(R.id.btn_selected_all);
        btn_selected_all_delete = (Button) findViewById(R.id.btn_selected_delete_all);
        toast= (Button) findViewById(R.id.toast);
        gridAdapter = new GridAdapter(this, list);

        gridView.setAdapter(gridAdapter);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(DialogActivity.this).inflate(R.layout.dialog_view_item, null);
                al = new AlertDialog.Builder(DialogActivity.this);
                al.setIcon(R.mipmap.ic_launcher);
                al.setTitle("5555");

                Button button = (Button) view.findViewById(R.id.btn_dialog_item);

                al.setView(view);
                al.create();
                al.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DialogActivity.this, "444", Toast.LENGTH_SHORT).show();
                        al.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                dialog.dismiss();
                            }
                        });
                    }
                });

//                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this)
//                        .setTitle("")
//                        .setIcon(R.mipmap.ic_launcher)
//                        .setMessage("")
//                        .setPositiveButton("完成", null)
//                        .setNegativeButton("取消", null);
//                builder.create().show();
            }
        });

        bnt_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = gridAdapter.getList();
                sb = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    sb.append(list.get(i) + ",");
                }
                Toast.makeText(DialogActivity.this, "集合为：" + sb, Toast.LENGTH_SHORT).show();
            }
        });

        //全选
        btn_selected_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridAdapter.setSelecteAll();
                gridAdapter.notifyDataSetChanged();
            }
        });

        btn_selected_all_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridAdapter.setSelecteAllDelere();
                gridAdapter.notifyDataSetChanged();

            }
        });


        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("测试toast"+i);
                i++;
            }
        });
    }

    private void initData() {
        for (int i = 1; i < 32; i++) {
            list.add(new Item(i, "i"));
        }
    }


}
